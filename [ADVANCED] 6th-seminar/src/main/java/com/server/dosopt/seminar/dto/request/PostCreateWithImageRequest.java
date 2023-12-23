package com.server.dosopt.seminar.dto.request;

public record PostCreateWithImageRequest(
        String title,
        String content,
        String fileName) {
    static public PostCreateWithImageRequest of(String title, String content, String fileName) {
        return new PostCreateWithImageRequest(title, content, fileName);
    }
}
