package com.server.dosopt.seminar.common.exception.model;

import com.server.dosopt.seminar.common.response.Error;

public class BadRequestException extends BusinessException {
    public BadRequestException(Error error, String message) {
        super(error, message);
    }
}
