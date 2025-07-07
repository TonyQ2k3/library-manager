package com.example.library.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;


// Test the BookService class
public class BookServiceTest {
    @Mock // Mock the BookRepository to simulate database interactions
    private BookRepository bookRepository;

    @InjectMocks // Inject the mocked BookRepository into the BookService
    private BookService bookService;

    private Book exampleBook;

    // Initialize Mockito annotations before each test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks
        exampleBook = new Book(1L, "Example Title", "Example Author", "1234567890", true);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = List.of(exampleBook);
        when(bookRepository.findAll()).thenReturn(books); // Mock the repository method to return a list of books

        List<Book> result = bookService.getAllBooks(); // Call the service method
        assertEquals(1, result.size()); // Verify the size of the returned list
        assertEquals(exampleBook, result.get(0)); // Verify the content of the returned book
        verify(bookRepository, times(1)).findAll(); // Verify that the repository method was called once
    }

    @Test
    void testGetBooksByTitle() {
        List<Book> books = List.of(exampleBook);
        when(bookRepository.findByTitleContainingIgnoreCase("Example")).thenReturn(books); // Mock the repository method

        List<Book> result = bookService.getBooksbyTitle("Example"); // Call the service method
        assertEquals(1, result.size()); // Verify the size of the returned list
        assertEquals(exampleBook, result.get(0)); // Verify the content of the returned book
        verify(bookRepository, times(1)).findByTitleContainingIgnoreCase("Example");
    }
}
