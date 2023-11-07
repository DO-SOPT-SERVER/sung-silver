package com.server.dosopt.seminar.common.response;

import com.server.dosopt.seminar.dto.ErrorType.ErrorType;
import com.server.dosopt.seminar.dto.ErrorType.SuccessType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ApiResponse<T> {
    private final int code;
    private final String message;
    private T data; // response에 담길 타입을 특정할 수 없으므로 제네릭 사용

    public static ApiResponse success(Success success) { // 성공 시 반환할 data가 없을 수도 있음
        return new ApiResponse<>(success.getHttpStatus(), success.getMessage());
    }

    public static <T> ApiResponse<T> success(Success success, T data) { // 반환할 data가 있는 경우
        return new ApiResponse<T>(success.getHttpStatus(), success.getMessage(), data);
    }

    public static ApiResponse error(Error error) {
        return new ApiResponse<>(error.getHttpStatus(), error.getMessage());
    }

}
