package com.example.seminar.dto;

public record PostRequestV3(
        @ValidTitle
        String title,
        String content
) {
}
