package com.example.BookLibrary;

import com.example.BookLibrary.Entities.Borrow;
import com.example.BookLibrary.Service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

        @Autowired
        private BorrowService borrowService;

        @PostMapping
        public ResponseEntity<Borrow> borrowBook(@RequestBody BorrowRequest request) {
            try {
                Borrow borrow = borrowService.borrowBook(request.getBookId(), request.getMemberId());
                return ResponseEntity.ok(borrow);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @PostMapping("/return/{borrowId}")
        public ResponseEntity<Borrow> returnBook(@PathVariable Long borrowId) {
            try {
                Borrow borrow = borrowService.returnBook(borrowId);
                return ResponseEntity.ok(borrow);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @GetMapping("/active")
        public ResponseEntity<List<Borrow>> getBorrowedBooks() {
            List<Borrow> borrowedBooks = borrowService.getBorrowedBooks();
            return ResponseEntity.ok(borrowedBooks);
        }

        @GetMapping("/member/{userId}")
        public ResponseEntity<List<Borrow>> getUserHistory(@PathVariable Long userId) {
            List<Borrow> history = borrowService.getUserBorrowHistory(userId);
            return ResponseEntity.ok(history);
        }

        @GetMapping("/all")
        public ResponseEntity<List<Borrow>> getAllBorrows() {
            List<Borrow> allBorrows = borrowService.getAllBorrows();
            return ResponseEntity.ok(allBorrows);
        }
    }

    // Simple Request DTO
    class BorrowRequest {
        private Long bookId;
        private Long userId;

        // Getters and setters
        public Long getBookId() { return bookId; }
        public void setBookId(Long bookId) { this.bookId = bookId; }

        public Long getMemberId() { return userId; }
        public void setMemberId(Long userId) { this.userId = userId; }
    }

