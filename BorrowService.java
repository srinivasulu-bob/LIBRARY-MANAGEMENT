package com.example.BookLibrary.Service;

import com.example.BookLibrary.Entities.Book;
import com.example.BookLibrary.Entities.Borrow;
import com.example.BookLibrary.Entities.Users;
import com.example.BookLibrary.Repository.BookRepo;
import com.example.BookLibrary.Repository.BorrowRepo;
import com.example.BookLibrary.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {

        @Autowired
        private BorrowRepo borrowRepository;

        @Autowired
        private BookRepo bookRepository;

        @Autowired
        private UsersRepo memberRepository;

        public Borrow borrowBook(Long bookId, Long memberId) {
            // Check if book exists and is available
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found"));


            // Check if member exists
            Users user = memberRepository.findById(memberId)
                    .orElseThrow(() -> new RuntimeException("Member not found"));

            // Check if book is already borrowed
            Optional<Borrow> existingBorrow = borrowRepository.findByBookIdAndStatus(bookId, "BORROWED");
            if (existingBorrow.isPresent()) {
                throw new RuntimeException("Book is already borrowed");
            }

            // Create borrow record
            Borrow borrow = new Borrow();
            borrow.setBook(book);
            borrow.setBorrowDate(LocalDate.now());
            borrow.setDueDate(LocalDate.now().plusDays(14)); // 2 weeks borrowing period
            borrow.setStatus("BORROWED");

            return borrowRepository.save(borrow);
        }

        public Borrow returnBook(Long borrowId) {
            Borrow borrow = borrowRepository.findById(borrowId)
                    .orElseThrow(() -> new RuntimeException("Borrow record not found"));

            if ("RETURNED".equals(borrow.getStatus())) {
                throw new RuntimeException("Book already returned");
            }

            // Update borrow record
            borrow.setReturnDate(LocalDate.now());
            borrow.setStatus("RETURNED");

            // Update book availability
            Book book = borrow.getBook();
            bookRepository.save(book);

            return borrowRepository.save(borrow);
        }

        public List<Borrow> getBorrowedBooks() {
            return borrowRepository.findByStatus("BORROWED");
        }

        public List<Borrow> getUserBorrowHistory(Long userId) {
            return borrowRepository.findByUserId(userId);
        }

        public List<Borrow> getAllBorrows() {
            return borrowRepository.findAll();
        }
    }
