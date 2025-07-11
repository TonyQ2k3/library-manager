package com.example.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Book;
import com.example.library.service.BookService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookbyId(@PathVariable String id) {
    // Assuming the id is a String, adjust the type if necessary
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Book ID must not be null or empty");
        }
        // Convert string to long
        long bookId;
        try {
            bookId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid book ID format: " + id, e);
        }
        return bookService.getBookById(bookId);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
    
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Book ID must not be null or empty");
        }
        // Convert string to long
        long bookId;
        try {
            bookId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid book ID format: " + id, e);
        }
        bookService.deleteBook(bookId);
    }
}
