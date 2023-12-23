package com.example.seminar.dto;


import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = TitleValidator.class)
@Target({java.lang.annotation.ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface ValidTitle {

    String message() default "Invalid title";

    String pattern() default "[가-힣|a-z|A-Z|0-9|]";
}
