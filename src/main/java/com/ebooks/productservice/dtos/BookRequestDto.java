package com.ebooks.productservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class BookRequestDto {
    @NotBlank
    private String title;
    private String author;
    @Size(min = 3, max = 90)
    private String description;
    private BigDecimal price;
    @NotNull
    @Positive
    private Integer stock;

    public BookRequestDto(String title, String author, String description, BigDecimal price, Integer stock) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
