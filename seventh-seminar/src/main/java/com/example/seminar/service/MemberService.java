package com.example.seminar.service;


import com.example.seminar.controller.MemberController;
import com.example.seminar.dto.MemberCreateRequest;
import com.example.seminar.entity.MemberEntity;
import com.example.seminar.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    public void create(MemberCreateRequest request) {
        MemberEntity member = MemberEntity.builder()
                .name(request.name())
                .age(request.age())
                .build();
        System.out.println(member);
        memberJpaRepository.save(member);
    }
}
