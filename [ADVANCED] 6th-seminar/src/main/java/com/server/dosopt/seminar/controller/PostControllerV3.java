package com.server.dosopt.seminar.controller;

import com.server.dosopt.seminar.dto.request.PostCreateRequest;
import com.server.dosopt.seminar.dto.request.PostCreateWithImageRequest;
import com.server.dosopt.seminar.dto.response.ImagePresignedUrlResponse;
import com.server.dosopt.seminar.service.PostServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("/api/v3/posts")
@RequiredArgsConstructor
public class PostControllerV3 {

    private static final String CUSTOM_AUTH_ID = "X-Auth-Id";
    private final PostServiceV2 postService;

    @GetMapping("/image/presigned")
    public ResponseEntity<ImagePresignedUrlResponse> getPreSignedUrl() {
        return ResponseEntity.ok(postService.getUploadPresignedUrl());
    }

    @PostMapping
    public ResponseEntity<Void> createPostV3(@RequestHeader(CUSTOM_AUTH_ID) Long memberId, PostCreateWithImageRequest request) {
        URI location = URI.create("/api/posts/v3" + postService.createV3(request,memberId));
        return ResponseEntity.created(location).build();
    }
}
