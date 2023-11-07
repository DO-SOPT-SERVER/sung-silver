package com.server.dosopt.seminar.dto.response;

public record PostUriResponse(String uri) {
    public static PostUriResponse of(String uri) {
        return new PostUriResponse(uri);
    }
}
