package com.server.dosopt.seminar.common.dto;

import com.server.dosopt.seminar.dto.ErrorType.ErrorType;
import com.server.dosopt.seminar.dto.ErrorType.SuccessType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ApiResponseDTO<T> {
    private final int code;
    private final String message;
    private T data; // response에 담길 타입을 특정할 수 없으므로 제네릭 사용

    public static ApiResponseDTO success(SuccessType success) { // 성공 시 반환할 data가 없을 수도 있음
        return new ApiResponseDTO<>(success.getHttpsStatusCode(), success.getMessage());
    }

    public static <T> ApiResponseDTO<T> success(SuccessType success, T data) { // 반환할 data가 있는 경우
        return new ApiResponseDTO<T>(success.getHttpsStatusCode(), success.getMessage(), data);
    }

    public static ApiResponseDTO error(ErrorType error) {
        return new ApiResponseDTO<>(error.getHttpsStatusCode(), error.getMessage());
    }

}
