package com.ebooks.productservice.services.mapper;

import com.ebooks.productservice.dtos.BookRequestDto;
import com.ebooks.productservice.dtos.BookResponseDto;
import com.ebooks.productservice.entities.Book;

import java.util.List;

//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
//public interface BookMapper {
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    Book toEntity(BookRequestDto bookRequestDto);
//    BookResponseDto toResponse(Book book);
//    List<BookResponseDto> toResponseList(List<Book> book);
//
//}

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookDtoMapper {

    public Book toEntity(BookRequestDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setDescription(dto.getDescription());
        book.setPrice(dto.getPrice());
        book.setStock(dto.getStock());
        // createdAt will be set in service layer
        return book;
    }

    public BookResponseDto toResponse(Book book) {
        BookResponseDto response = new BookResponseDto();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setDescription(book.getDescription());
        response.setPrice(book.getPrice());
        response.setStock(book.getStock());
        response.setCreatedAt(book.getCreatedAt());
        return response;
    }

    public List<BookResponseDto> toResponseList(List<Book> books) {
        return books.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
