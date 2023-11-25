package com.server.dosopt.seminar.dto.ErrorType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessType {
    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED, "사용자 생성에 성공했습니다"),
    GET_ALL_MEMBER_INFO(HttpStatus.OK, "전체 사용자 조회에 성공했습니다"),
    GET_MEMBER_INFO(HttpStatus.OK, "사용자 조회에 성공했습니다"),
    DELETE_MEMBER(HttpStatus.OK, "사용자 삭제에 성공했습니다"),
    UPDATE_MEMBER_INFO(HttpStatus.OK, "사용자 정보 수정에 성공했습니다");

    private final HttpStatus status;
    private final String message;
    public int getHttpsStatusCode(){return status.value();}
}
