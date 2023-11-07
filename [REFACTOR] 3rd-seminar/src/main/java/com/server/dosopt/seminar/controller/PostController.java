package com.server.dosopt.seminar.controller;

import com.server.dosopt.seminar.common.response.ApiResponse;
import com.server.dosopt.seminar.common.response.Success;
import com.server.dosopt.seminar.dto.request.PostCreateRequest;
import com.server.dosopt.seminar.dto.request.PostUpdateRequest;
import com.server.dosopt.seminar.dto.response.PostGetResponse;
import com.server.dosopt.seminar.dto.response.PostUriResponse;
import com.server.dosopt.seminar.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private static final String CUSTOM_AUTH_ID = "X-Auth-Id";

    private final PostService postService;

    @GetMapping("{postId}")
    public ApiResponse<PostGetResponse> getPostById(@PathVariable Long postId) {
        return ApiResponse.success(Success.GET_POST_SUCCESS, postService.getById(postId));
    }

    @GetMapping
    public ApiResponse<List<PostGetResponse>> getPosts(@RequestHeader(CUSTOM_AUTH_ID) Long memberId) {
        return ApiResponse.success(Success.GET_POSTS_SUCCESS,postService.getPosts(memberId));
    }

    @PostMapping
    public ApiResponse<PostUriResponse> createPost(@RequestHeader(CUSTOM_AUTH_ID) Long memberId, @RequestBody PostCreateRequest request) {
        URI location = URI.create("/api/post/" + postService.create(request, memberId));
        return ApiResponse.success(Success.CREATE_POST_SUCCESS,PostUriResponse.of(location.toString()));
    }

    @PatchMapping("{postId}")
    public ApiResponse updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest request) {
        postService.editContent(postId, request);
        return ApiResponse.success(Success.UPDATE_POST_SUCCESS);
    }

    @DeleteMapping("{postId}")
    public ApiResponse deletePost(@PathVariable Long postId) {
        postService.deleteById(postId);
        return ApiResponse.success(Success.DELETE_POST_SUCCESS);
    }
}
