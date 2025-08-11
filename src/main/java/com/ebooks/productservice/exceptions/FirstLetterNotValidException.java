package com.ebooks.productservice.exceptions;

public class FirstLetterNotValidException extends RuntimeException {
    public FirstLetterNotValidException(String message) {
        super(message);
    }
}
