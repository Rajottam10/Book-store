package com.ebooks.productservice.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FirstLetterValidator.class)
public @interface FirstLetterValidation {
    public String message() default "The first letter of author must be uppercase";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
