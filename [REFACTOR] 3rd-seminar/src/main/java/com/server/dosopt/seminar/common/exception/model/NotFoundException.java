package com.server.dosopt.seminar.common.exception.model;

import com.server.dosopt.seminar.common.response.Error;

public class NotFoundException extends BusinessException{
    public NotFoundException(Error error, String message) {
        super(error, message);
    }
}
