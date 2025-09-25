package com.example.BookLibrary.Repository;

import com.example.BookLibrary.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users , Long> {

    Optional<Users> findByName(String name);
    Optional<Users> findByEmail(String email);
    boolean existsByName(String name);
    boolean existsByEmail(String email);
}
