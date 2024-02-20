package com.example.seminar.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UsernameValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
