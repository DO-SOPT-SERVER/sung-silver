package com.server.dosopt.seminar.common.exception.model;

import com.server.dosopt.seminar.common.response.Error;

public class BusinessException extends RuntimeException {
    private final Error error;
    public BusinessException(Error error,String message) {
        super(message);
        this.error = error;
    }

    public int getHttpStatus() {
        return error.getHttpStatus();
    }
}
