package com.server.dosopt.seminar.dto.request;

import com.server.dosopt.seminar.domain.Part;

public record
MemberProfileUpdateRequestDTO(int generation, Part part) {
    public static MemberProfileUpdateRequestDTO of(int generation, Part part) {
        return new MemberProfileUpdateRequestDTO(generation, part);
    }
}
