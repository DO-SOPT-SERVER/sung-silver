package com.server.dosopt.seminar.dto.response;

public record UriResponse(String uri) {
    public static UriResponse of(String uri) {
        return new UriResponse(uri);
    }
}
