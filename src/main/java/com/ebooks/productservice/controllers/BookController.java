package com.ebooks.productservice.controllers;

import com.ebooks.productservice.dtos.BookRequestDto;
import com.ebooks.productservice.dtos.BookResponseDto;
import com.ebooks.productservice.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    @PostMapping("/create")
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookRequestDto request) {
        BookResponseDto bookResponseDto = bookService.createBook(request);
        System.out.println(bookResponseDto);
        return ResponseEntity.ok(bookResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id,@Valid @RequestBody BookRequestDto request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cache/clear")
    public ResponseEntity<String> clearCache() {
        bookService.clearAllBooksCache();
        return ResponseEntity.ok("Books cache cleared.");
    }

    @GetMapping("/trace")
    public String trace(){
        return "Tracing success!";
    }
}