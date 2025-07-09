package com.example.library.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.library.exception.BookUnavailableException;
import com.example.library.model.Book;
import com.example.library.model.BorrowingRecord;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowingRecordRepository;


// Test the BookService class
public class BookServiceTest {
    @Mock // Mock the BookRepository to simulate database interactions
    private BookRepository bookRepository;

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

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

    @Test
    void testBorrowBook_success() {
        User user = new User();
        Book book = new Book(1L, "Test Book", "Test Author", "1234567890", true);
        
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book)); // Mock the repository to return the book
        bookService.borrowBook(1L, user);
        
        // Book should be updated to not available
        verify(bookRepository).save(book);
        // Verify that a borrowing record was created
        verify(borrowingRecordRepository).save(org.mockito.ArgumentMatchers.any(BorrowingRecord.class));
    }

    @Test
    void testBorrowBook_alreadyBorrowed() {
        User user = new User();
        Book book = new Book(1L, "Test Book", "Test Author", "1234567890", false);
        
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book)); // Mock the repository to return the book
        
        // Expect an exception when trying to borrow an already borrowed book
        assertThrows(BookUnavailableException.class, () -> bookService.borrowBook(1L, user));
        
        // Verify that the book was not saved again
        verify(bookRepository, times(0)).save(book);
    }

    @Test
    void testReturnBook_success() {
        User user = new User();
        Book book = new Book(1L, "Test Book", "Test Author", "1234567890", false);
        BorrowingRecord record = new BorrowingRecord();
        
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book));
        when(borrowingRecordRepository.findTopByBookAndUserOrderByBorrowedAtDesc(book, user)).thenReturn(Optional.of(record));
        bookService.returnBook(1L, user);

        // Book should be updated to available
        verify(bookRepository).save(book);
        // Verify that the borrowing record was updated
        verify(borrowingRecordRepository).save(org.mockito.ArgumentMatchers.any(BorrowingRecord.class));
    }
}
