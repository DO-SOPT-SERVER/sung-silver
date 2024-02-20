package com.example.seminar.domain;


import com.example.seminar.entity.CustomerEntity;
import com.example.seminar.repository.CustomerJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class CustomerEntityTest {

    @Nested
    @DisplayName("Customer 생성 관련 Test")
    class CustomerCreateTest {
        private static final String NAME = "홍길동";
        private static final int INVALID_AGE = -1;
        private static final int AGE = 20;
        private static final String NICKNAME = "hong";

        @DisplayName("나이가 1살 보다 작거나, 200살 보다 큰 경우 Customer 객체 생성을 실패한다.")
        @Test
        void createInvalidAgeCustomerTest() {
            assertThatThrownBy(() ->
                    CustomerEntity.builder()
                            .name(NAME)
                            .age(INVALID_AGE)
                            .nickname(NICKNAME)
                            .build())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("나이는 1살부터 200살 사이로 설정해야합니다.");
        }

        @DisplayName("닉네임은 null인 경우 Customer 객체 생성을 실패한다.")
        @Test
        void createNullNicknameCustomerTest() {
            assertThatThrownBy(() ->
                    CustomerEntity.builder()
                            .name(NAME)
                            .age(AGE)
                            .nickname(null)
                            .build())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("닉네임은 널이면 안됩니다.");
        }

        @DisplayName("이름은 null인 경우 Customer 객체 생성을 실패한다.")
        @Test
        void createNullNameCustomerTest() {
            assertThatThrownBy(() ->
                    CustomerEntity.builder()
                            .name(null)
                            .age(AGE)
                            .nickname(NICKNAME)
                            .build())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 널이면 안됩니다.");
        }
    }
}
