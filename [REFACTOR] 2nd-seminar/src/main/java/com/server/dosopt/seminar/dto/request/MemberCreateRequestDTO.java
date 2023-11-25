package com.server.dosopt.seminar.dto.request;

import com.server.dosopt.seminar.domain.SOPT;

public record MemberCreateRequestDTO(
        String name,
        String nickname,
        int age,
        SOPT sopt) {
    public static MemberCreateRequestDTO of(String name, String nickname, int age, SOPT sopt) {
        return new MemberCreateRequestDTO(name, nickname, age, sopt);
    }
}
