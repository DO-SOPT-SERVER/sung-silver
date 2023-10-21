package com.server.dosopt.seminar.dto.response;

import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.domain.SOPT;

public record MemberGetResponseDTO(
        String name,
        String nickname,
        int age,
        SOPT soptInfo
) {
    public static MemberGetResponseDTO of(Member member) {
        return new MemberGetResponseDTO(
                member.getName(),
                member.getNickname(),
                member.getAge(),
                member.getSopt()
        );
    }
}
