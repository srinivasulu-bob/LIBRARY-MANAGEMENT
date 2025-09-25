package com.example.BookLibrary.Repository;

import com.example.BookLibrary.Entities.Book;
import com.example.BookLibrary.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book , Long> {

    List<Book> findByAuthor(String author);
    List<Book> findByTitleContaining(String title);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByBorrower(Users borrower);
    List<Book> findByIsBorrowedFalse(); // Available books
    List<Book> findByIsBorrowedTrue();
}
