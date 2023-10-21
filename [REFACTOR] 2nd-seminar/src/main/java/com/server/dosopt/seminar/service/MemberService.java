package com.server.dosopt.seminar.service;

import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.domain.SOPT;
import com.server.dosopt.seminar.dto.request.MemberCreateRequestDTO;
import com.server.dosopt.seminar.dto.request.MemberProfileUpdateRequestDTO;
import com.server.dosopt.seminar.dto.response.MemberGetResponseDTO;
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

    public MemberGetResponseDTO getMemberByIdV1(Long id) {
        Member member = memberJpaRepository.findById(id).get();
        return MemberGetResponseDTO.of(member);
    }

    public MemberGetResponseDTO getMemberByIdV2(Long id) {
        return MemberGetResponseDTO.of(memberJpaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 회원입니다.")));
    }

    public MemberGetResponseDTO getMemberByIdV3(Long id) {
        return MemberGetResponseDTO.of(memberJpaRepository.findByIdOrThrow(id));
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
                .name(request.getName())
                .nickname(request.getNickname())
                .age(request.getAge())
                .sopt(request.getSopt())
                .build());
        return member.getId().toString();
    }

    @Transactional
    public void updateSOPT(Long memberId, MemberProfileUpdateRequestDTO request) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        member.updateSOPT(new SOPT(request.getGeneration(), request.getPart()));
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        memberJpaRepository.delete(member);
    }
}