package com.ebooks.productservice.controllers;

import com.ebooks.productservice.dtos.BookRequestDto;
import com.ebooks.productservice.dtos.BookResponseDto;
import com.ebooks.productservice.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@Import(BookControllerTest.MockConfig.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookService bookService;

    @Test
    public void shouldCreateBookSuccessfully() throws Exception {
        BookRequestDto requestDto = new BookRequestDto(
                "foneloan", "Declan Rice", "top corners", new BigDecimal("49.00"), 10
        );

        BookResponseDto response = new BookResponseDto(
                "Clean Code", "Robert Martin", "Best practices", new BigDecimal("49.99"), 10
        );

        when(bookService.createBook(any(BookRequestDto.class))).thenReturn(response);

        // When & Then
        mockMvc.perform(post("/api/books/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.price").value(49.99));
    }

    @TestConfiguration
    static class MockConfig {
        @Bean
        public BookService bookService() {
            return mock(BookService.class);
        }
    }
}
