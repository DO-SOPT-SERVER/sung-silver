package com.server.dosopt.seminar.dto.response;

public record ImagePresignedUrlResponse(
        String fileName,
        String url) {
    static public ImagePresignedUrlResponse of(String fileName, String url) {
        return new ImagePresignedUrlResponse(fileName, url);
    }
}
