package com.example.seminar.controller;


import com.example.seminar.dto.PostRequest;
import com.example.seminar.dto.PostRequestV2;
import com.example.seminar.dto.PostRequestV3;
import com.example.seminar.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@Valid @RequestBody PostRequest request) {
        postService.create(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/v2")
    public ResponseEntity<Void> createPostV3(@Valid @RequestBody PostRequestV2 request) {
        postService.createV2(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/v3")
    public ResponseEntity<Void> createPostV3(@Valid @RequestBody PostRequestV3 request) {
        postService.createV3(request);
        return ResponseEntity.ok().build();
    }
}