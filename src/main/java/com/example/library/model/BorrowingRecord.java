package com.example.library.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingRecord {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne // This annotation establishes a many-to-one relationship with the User entity, meaning multiple borrowing records can be associated with one user.
    private User user;

    @ManyToOne
    private Book book;

    private LocalDateTime borrowedAt; // The date and time when the book was borrowed.
    private LocalDateTime returnedAt; // The date and time when the book was returned,
}
