package com.server.dosopt.seminar.service;

import com.server.dosopt.seminar.common.exception.model.NotFoundException;
import com.server.dosopt.seminar.common.response.Error;
import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.domain.Post;
import com.server.dosopt.seminar.dto.request.PostCreateRequest;
import com.server.dosopt.seminar.dto.request.PostUpdateRequest;
import com.server.dosopt.seminar.dto.response.PostGetResponse;
import com.server.dosopt.seminar.repository.MemberJpaRepository;
import com.server.dosopt.seminar.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostJpaRepository postJpaRepository;
    private final MemberJpaRepository memberJpaRepository;


    @Transactional
    public String create(PostCreateRequest request, Long memberId) {
        Member member = memberJpaRepository.findById(memberId).orElseThrow(()->new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION,Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Post post = postJpaRepository.save(
                Post.builder()
                        .member(member)
                        .title(request.title())
                        .content(request.content()).build());
        return post.getId().toString();
    }

    @Transactional
    public void editContent(Long postId, PostUpdateRequest request) {
        Post post = postJpaRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_POST_EXCEPTION,Error.NOT_FOUND_POST_EXCEPTION.getMessage()));
        post.updateContent(request.content());
    }

    @Transactional
    public void deleteById(Long postId) {
        Post post = postJpaRepository.findById(postId).orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_POST_EXCEPTION,Error.NOT_FOUND_POST_EXCEPTION.getMessage()));
        postJpaRepository.delete(post);
    }

    public List<PostGetResponse> getPosts(Long memberId) {
        return postJpaRepository.findAllByMemberId(memberId)
                .stream()
                .map(post -> PostGetResponse.of(post))
                .toList();
    }

    public PostGetResponse getById(Long postId) {
        Post post = postJpaRepository.findById(postId).orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_POST_EXCEPTION,Error.NOT_FOUND_POST_EXCEPTION.getMessage()));
        return PostGetResponse.of(post);
    }
}