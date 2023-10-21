package com.server.dosopt.seminar.dto.response;

import com.server.dosopt.seminar.domain.Part;
public record MemberProfileUpdateResponseDTO(int generation, Part part) {
    public static MemberProfileUpdateResponseDTO of(int generation, Part part) {
        return new MemberProfileUpdateResponseDTO(generation, part);
    }
}
