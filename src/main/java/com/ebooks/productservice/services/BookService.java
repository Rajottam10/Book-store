package com.ebooks.productservice.services;

import com.ebooks.productservice.dtos.BookRequestDto;
import com.ebooks.productservice.dtos.BookResponseDto;

import java.util.List;

public interface BookService {
    BookResponseDto createBook(BookRequestDto bookReq);
    BookResponseDto getBookById(Long id);
    List<BookResponseDto> getAllBooks();
    BookResponseDto updateBook(Long id, BookRequestDto bookDReq);
    void deleteBook(Long id);
}
