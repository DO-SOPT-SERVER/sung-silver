package com.server.dosopt.seminar.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Success {

    // 200 OK
    GET_POST_SUCCESS(HttpStatus.OK, "게시물 조회에 성공했습니다"),
    GET_POSTS_SUCCESS(HttpStatus.OK, "게시물 목록 조회에 성공했습니다"),

    // 201 craeted
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료됐습니다"),
    CREATE_POST_SUCCESS(HttpStatus.CREATED, "게시물 생성이 완료됐습니다"),

    // 204 no content
    UPDATE_POST_SUCCESS(HttpStatus.NO_CONTENT, "게시물 수정이 완료됐습니다"),
    DELETE_POST_SUCCESS(HttpStatus.NO_CONTENT, "게시물 삭제가 완료됐습니다");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatus(){
        return httpStatus.value();
    }

    public String getMessage(){
        return message;
    }
}
