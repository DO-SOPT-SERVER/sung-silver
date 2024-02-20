package com.server.dosopt.seminar.service;

import com.server.dosopt.seminar.VO.PreSignedUrlVO;
import com.server.dosopt.seminar.common.exception.BusinessException;
import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.domain.Post;
import com.server.dosopt.seminar.dto.request.PostCreateRequest;
import com.server.dosopt.seminar.dto.request.PostCreateWithImageRequest;
import com.server.dosopt.seminar.dto.response.ImagePresignedUrlResponse;
import com.server.dosopt.seminar.external.s3.S3Service;
import com.server.dosopt.seminar.repository.MemberJpaRepository;
import com.server.dosopt.seminar.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceV2 {

    private static final String POST_IMAGE_FOLDER_NAME = "posts/";

    private final MemberJpaRepository memberJpaRepository;
    private final PostJpaRepository postJpaRepository;
    private final S3Service s3Service;

    @Transactional
    public String createV3(PostCreateWithImageRequest request, Long memberId){
        try {
            String imageUrl = s3Service.getURL(POST_IMAGE_FOLDER_NAME + request.fileName());
            Member member = memberJpaRepository.findByIdOrThrow(memberId);

            Post post = postJpaRepository.save(
                    Post.builderWithImageUrl()
                            .title(request.title())
                            .content(request.content())
                            .imageUrl(imageUrl)
                            .member(member)
                            .build()
            );

            return post.getId().toString();
        } catch (RuntimeException e) {
            // 게시글 저장에 실패 할 시 S3에 미리 업로드 되어 있던 이미지를 삭제한다
            try {
                s3Service.deleteImage(POST_IMAGE_FOLDER_NAME + "/" + request.fileName());
            } catch (IOException io) {
                // 이미지 삭제 과정에서 에러가 발생할 경우
                throw new RuntimeException("Failed to delete image from S3: " + io.getMessage());
            }
            throw e;
        }
    }

    @Transactional
    public String createV2(PostCreateRequest request, MultipartFile image, Long memberId) {
        try {
            final String imageUrl = s3Service.uploadImage(POST_IMAGE_FOLDER_NAME, image);
            Member member = memberJpaRepository.findByIdOrThrow(memberId);
            Post post = postJpaRepository.save(
                    Post.builderWithImageUrl()
                            .title(request.title())
                            .content(request.content())
                            .imageUrl(imageUrl)
                            .member(member)
                            .build()
            );
            return post.getId().toString();
        } catch (RuntimeException |
                 IOException e) {// IOException와 RuntimeException을 묶어서 처리했음 checked/unchecked 예외처리 알아보기
            throw new RuntimeException(e.getMessage()); // 서비스 로직에 맞추어 변경 가능
        }
    }

    @Transactional
    public void deleteByIdV2(Long postId) {
        try {
            Post post = postJpaRepository.findById(postId)
                    .orElseThrow(() -> new BusinessException("해당하는 게시글이 없습니다."));
            s3Service.deleteImage(post.getImageUrl());
            postJpaRepository.deleteById(postId);
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String create(PostCreateRequest request, Long memberId) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        Post post = postJpaRepository.save(
                Post.builderWithImageUrl()
                        .title(request.title())
                        .content(request.content())
                        .member(member)
                        .build()
        );
        return post.getId().toString();
    }

    public ImagePresignedUrlResponse getUploadPresignedUrl() {
        try {
            PreSignedUrlVO presignedUrlVO = s3Service.getUploadPreSignedUrl(POST_IMAGE_FOLDER_NAME);
            return new ImagePresignedUrlResponse(
                    presignedUrlVO.getFileName(),
                    presignedUrlVO.getUrl()
            );
        } catch (RuntimeException e) {
            throw new BusinessException("url 조회 실패");
        }
    }
}
