package com.example.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a book is unavailable for borrowing.
 * This exception is used to indicate that the requested book cannot be borrowed
 * because it is already checked out or not available in the library.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookUnavailableException extends RuntimeException {
    public BookUnavailableException(String message) {
        super(message);
    }
}