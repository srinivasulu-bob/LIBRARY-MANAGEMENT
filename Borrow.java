package com.example.BookLibrary.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrow")
public class Borrow {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "book_id")
        private Book book;

        @ManyToOne
        @JoinColumn(name = "member_id")
        private Users user;

        private LocalDate borrowDate;
        private LocalDate dueDate;
        private LocalDate returnDate;
        private String status; // "BORROWED" or "RETURNED"

        // Constructors
        public Borrow() {}

        public Borrow(Book book, Users user, LocalDate borrowDate, LocalDate dueDate) {
            this.book = book;
            this.user=user;
            this.borrowDate = borrowDate;
            this.dueDate = dueDate;
            this.status = "BORROWED";
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public Book getBook() { return book; }
        public void setBook(Book book) { this.book = book; }

        public Users getMember() { return user; }
        public void setMember(Users user) { this.user=user; }

        public LocalDate getBorrowDate() { return borrowDate; }
        public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

        public LocalDate getDueDate() { return dueDate; }
        public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

        public LocalDate getReturnDate() { return returnDate; }
        public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
