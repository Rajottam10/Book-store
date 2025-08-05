package com.ebooks.productservice.services.impl;

import com.ebooks.productservice.dtos.BookRequestDto;
import com.ebooks.productservice.dtos.BookResponseDto;
import com.ebooks.productservice.entities.Book;
import com.ebooks.productservice.exceptions.BookAlreadyExistsException;
import com.ebooks.productservice.exceptions.BookNotFoundException;
import com.ebooks.productservice.repositories.BookRepository;
import com.ebooks.productservice.services.BookService;
import com.ebooks.productservice.services.mapper.BookDtoMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookDtoMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookDtoMapper bookMapper){
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional
    public BookResponseDto createBook(BookRequestDto bookReq) {
        Book book = bookMapper.toEntity(bookReq);
        existingBookCheck(bookReq);
        book.setCreatedAt(LocalDateTime.now());
        Book savedBook = bookRepository.save(book);
        System.out.println(savedBook.toString());
        return bookMapper.toResponse(savedBook);
    }

    @Override
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        return bookMapper.toResponse(book);
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookMapper.toResponseList(bookRepository.findAll());
    }

    @Override
    @Transactional
    public BookResponseDto updateBook(Long id, BookRequestDto bookReq) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        book.setTitle(bookReq.getTitle());
        book.setAuthor(bookReq.getAuthor());
        book.setDescription(bookReq.getDescription());
        book.setPrice(bookReq.getPrice());
        book.setStock(bookReq.getStock());
        existingBookCheck(bookReq);
        Book updated = bookRepository.save(book);
        return bookMapper.toResponse(updated);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public void existingBookCheck(BookRequestDto bookReq){
        Book existingBook = bookRepository.findBooksByTitle(bookReq.getTitle());
        if(existingBook != null){
            throw new BookAlreadyExistsException("The book with the title "+bookReq.getTitle()+" already exists.");
        }
    }
}
