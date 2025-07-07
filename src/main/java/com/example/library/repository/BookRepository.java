package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Additional query methods can be defined here if needed (name must follow Spring Data JPA conventions for automatic implementation)
    // For example, to find books by title or author, you can add methods like: 
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
}
