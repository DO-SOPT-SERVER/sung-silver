package com.server.dosopt.seminar.service;

import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.domain.SOPT;
import com.server.dosopt.seminar.dto.request.MemberCreateRequestDTO;
import com.server.dosopt.seminar.dto.request.MemberProfileUpdateRequestDTO;
import com.server.dosopt.seminar.dto.response.MemberGetResponseDTO;
import com.server.dosopt.seminar.dto.response.MemberProfileUpdateResponseDTO;
import com.server.dosopt.seminar.repository.MemberJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    public MemberGetResponseDTO getMemberById(Long id) {
        return MemberGetResponseDTO.of(memberJpaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 회원입니다.")));
    }

    public List<MemberGetResponseDTO> getMembers() {
        return memberJpaRepository.findAll()
                .stream()
                .map(MemberGetResponseDTO::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public String create(MemberCreateRequestDTO request) {
        Member member =  memberJpaRepository.save(Member.builder()
                .name(request.name())
                .nickname(request.nickname())
                .age(request.age())
                .sopt(request.sopt())
                .build());
        return member.getId().toString();
    }

    @Transactional
    public MemberProfileUpdateResponseDTO updateSOPT(Long memberId, MemberProfileUpdateRequestDTO request) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        member.updateSOPT(SOPT.builder().generation(request.generation()).part(request.part()).build());
        return MemberProfileUpdateResponseDTO.of(member.getSopt().getGeneration(), member.getSopt().getPart());
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        memberJpaRepository.delete(member);
    }
}
