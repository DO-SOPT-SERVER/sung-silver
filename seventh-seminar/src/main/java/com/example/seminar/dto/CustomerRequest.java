package com.example.seminar.dto;

public record CustomerRequest(
        String name,
        int age,
        String nickname
) {
}
