package com.example.library.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.exception.BookUnavailableException;
import com.example.library.model.Book;
import com.example.library.model.BorrowingRecord;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowingRecordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Lombok annotation to generate constructor with required arguments
public class BookService {
    private final BookRepository bookRepo;
    private final BorrowingRecordRepository borrowingRecordRepo;

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

    public void borrowBook(Long bookId, User user) {
        Book book = bookRepo.findById(bookId).orElseThrow();
        if (!book.isAvailable()) throw new BookUnavailableException("Book is already borrowed");

        book.setAvailable(false);
        bookRepo.save(book);

        BorrowingRecord record = new BorrowingRecord(null, user, book, LocalDateTime.now(), null);
        borrowingRecordRepo.save(record);
    }

    public void returnBook(Long bookId, User user) {
        Book book = bookRepo.findById(bookId).orElseThrow();
        if (book.isAvailable()) throw new RuntimeException("Book is not yet borrowed");

        book.setAvailable(true);
        bookRepo.save(book);

        BorrowingRecord record = borrowingRecordRepo.findTopByBookAndUserOrderByBorrowedAtDesc(book, user)
            .orElseThrow();
        record.setReturnedAt(LocalDateTime.now());
        borrowingRecordRepo.save(record);
    }
}
