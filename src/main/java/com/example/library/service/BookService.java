package com.example.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Lombok annotation to generate constructor with required arguments
public class BookService {
    private final BookRepository bookRepo;

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    } 

    public List<Book> getBooksbyTitle(String title) {
        return bookRepo.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> getBooksbyAuthor(String author) {
        return bookRepo.findByAuthorContainingIgnoreCase(author);
    }

    public Book getBookById(Long id) {
        return bookRepo.findById(id).orElse(null);
    }

    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }
}
