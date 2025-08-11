package com.ebooks.productservice.services.impl;

import com.ebooks.productservice.config.kafka.ProducerService;
import com.ebooks.productservice.dtos.BookRequestDto;
import com.ebooks.productservice.dtos.BookResponseDto;
import com.ebooks.productservice.entities.Book;
import com.ebooks.productservice.exceptions.BookAlreadyExistsException;
import com.ebooks.productservice.exceptions.BookNotFoundException;
import com.ebooks.productservice.repositories.BookRepository;
import com.ebooks.productservice.services.BookService;
import com.ebooks.productservice.services.mapper.BookDtoMapper;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookDtoMapper bookMapper;
    private final ProducerService producerService;

    public BookServiceImpl(BookRepository bookRepository, BookDtoMapper bookMapper, ProducerService producerService){
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.producerService = producerService;
    }

    @Override
    @Transactional
    public BookResponseDto createBook(BookRequestDto bookReq) {
        Book book = bookMapper.toEntity(bookReq);
        existingBookCheck(bookReq);
        book.setCreatedAt(LocalDateTime.now());
        Book savedBook = bookRepository.save(book);
        System.out.println(savedBook.toString());
        producerService.sendMsgToTopic(bookMapper.toResponse(savedBook));
        return bookMapper.toResponse(savedBook);
    }

    @Override
    @Cacheable(value = "books", key = "#id")
    public BookResponseDto getBookById(Long id) {
        simulateSlowService();
        System.out.println("Fetching from DB for ID: " + id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        producerService.sendMsgToTopic(bookMapper.toResponse(book));
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
        Book updated = bookRepository.save(book);
        return bookMapper.toResponse(updated);
    }

    @Override
    @CacheEvict(value = "books", key = "#id")
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @CacheEvict(value = "books", allEntries = true)
    public void clearAllBooksCache(){}

    private void simulateSlowService(){
        try {
            Thread.sleep(3000L);
        }catch (InterruptedException e){
            throw new IllegalStateException(e);
        }
    }

    public void existingBookCheck(BookRequestDto bookReq){
        Book existingBook = bookRepository.findBooksByTitle(bookReq.getTitle());
        if(existingBook != null){
            throw new BookAlreadyExistsException("The book with the title "+bookReq.getTitle()+" already exists.");
        }
    }
}
