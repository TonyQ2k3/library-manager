package com.example.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a book is not borrowed by a user.
 * This exception is used to indicate that the user is trying to return a book
 * that was not borrowed or is already available in the library.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookNotBorrowedException extends RuntimeException {
    public BookNotBorrowedException(String message) {
        super(message);
    }
}
