package com.ebooks.productservice.exceptions;

public class BookAlreadyExistsException extends RuntimeException{

    public BookAlreadyExistsException(String message){
        super(message);
    }
}
