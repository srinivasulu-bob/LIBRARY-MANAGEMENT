package com.example.BookLibrary.Repository;

import com.example.BookLibrary.Entities.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowRepo extends JpaRepository<Borrow , Long> {

        List<Borrow> findByUserId(Long userId);

        List<Borrow> findByBookId(Long bookId);

        List<Borrow> findByStatus(String status);

        Optional<Borrow> findByBookIdAndStatus(Long bookId, String status);
}
