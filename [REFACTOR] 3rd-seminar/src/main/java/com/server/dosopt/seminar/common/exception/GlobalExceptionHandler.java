package com.server.dosopt.seminar.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<Void> handleIllegalAccessException(final IllegalAccessException e) {
        return ResponseEntity.badRequest().build();
    }
}