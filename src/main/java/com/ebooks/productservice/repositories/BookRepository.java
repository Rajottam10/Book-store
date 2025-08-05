package com.ebooks.productservice.repositories;

import com.ebooks.productservice.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBooksByTitle(String title);
}
