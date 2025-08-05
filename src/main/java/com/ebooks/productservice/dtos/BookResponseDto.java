package com.ebooks.productservice.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
//@NoArgsConstructor
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private LocalDateTime createdAt;

    public BookResponseDto(String title, String author, String description, BigDecimal price, Integer stock) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
