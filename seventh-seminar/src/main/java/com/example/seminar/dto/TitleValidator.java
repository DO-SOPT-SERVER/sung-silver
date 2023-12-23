package com.example.seminar.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TitleValidator implements ConstraintValidator<ValidTitle, String> {

    private String pattern;

    @Override
    public void initialize(ValidTitle constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {

        // null, 공백으로만 이뤄지는 경우, 빈 값인 경우 ''
        if (title.isBlank()) {
            return false;
        }

        // 길이가 1보다 작거나 10보다 큰 경우
        if (title.isEmpty() || title.length() > 10) {
            return false;
        }

        // 첫 글자가 공백인 경우
        return !(title.charAt(0) == ' ');
    }
}
