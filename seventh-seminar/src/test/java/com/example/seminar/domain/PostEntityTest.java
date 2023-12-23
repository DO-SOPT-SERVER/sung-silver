package com.example.seminar.domain;

import com.example.seminar.entity.PostEntity;
import com.example.seminar.repository.PostJpaRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class PostEntityTest {

    @Autowired
    PostJpaRepository postJpaRepository;

    @Nested
    @DisplayName("Post 생성 관련 Test")
    class CreatePostEntityTest {
        private static final String TITLE = "테스트 제목";
        private static final String CONTENT = "테스트 내용";

        @DisplayName("제목이 null인 경우 Post 객체 생성을 실패한다.")
        @Test
        void test_createNullTitlePostEntity() {
            assertThatThrownBy(() ->
                    postJpaRepository.save(PostEntity.builder()
                                    .title(null)
                                    .content(CONTENT)
                                    .build()))
                            .isInstanceOf(ConstraintViolationException.class);
        }
    }
}
