package com.example.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.model.Book;
import com.example.library.model.BorrowingRecord;
import com.example.library.model.User;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    Optional<BorrowingRecord> findTopByBookAndUserOrderByBorrowedAtDesc(Book book, User user);
}
