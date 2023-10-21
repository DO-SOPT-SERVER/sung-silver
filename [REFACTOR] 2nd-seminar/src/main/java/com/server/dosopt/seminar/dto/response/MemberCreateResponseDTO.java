package com.server.dosopt.seminar.dto.response;

public record MemberCreateResponseDTO(String id) {
    public static MemberCreateResponseDTO of(String id) {
        return new MemberCreateResponseDTO(id);
    }
}
