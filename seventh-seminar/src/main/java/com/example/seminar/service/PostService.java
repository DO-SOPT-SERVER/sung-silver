package com.example.seminar.service;


import com.example.seminar.dto.PostRequest;
import com.example.seminar.dto.PostRequestV2;
import com.example.seminar.dto.PostRequestV3;
import com.example.seminar.entity.PostEntity;
import com.example.seminar.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostJpaRepository postJpaRepository;


    @Transactional
    public void create(PostRequest request) {
        PostEntity post = PostEntity.builder()
                .title("제목")
                .content("내용")
                .build();
        postJpaRepository.save(post);
    }

    @Transactional
    public void createV2(PostRequestV2 request) {
        PostEntity post = PostEntity.builder()
                .title("제목")
                .content("내용")
                .build();
        postJpaRepository.save(post);
    }

    @Transactional
    public void createV3(PostRequestV3 request) {
        PostEntity post = PostEntity.builder()
                .title("제목")
                .content("내용")
                .build();
        postJpaRepository.save(post);
    }

}
