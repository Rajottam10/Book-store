package com.ebooks.productservice.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FirstLetterValidator implements ConstraintValidator<FirstLetterValidation, String> {
    @Override
    public boolean isValid(String author, ConstraintValidatorContext constraintValidatorContext) {
        return Character.isUpperCase(author.charAt(0));
    }
}
