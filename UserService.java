package com.example.BookLibrary.Service;

import com.example.BookLibrary.Entities.Users;
import com.example.BookLibrary.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepo userRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<Users> getUserByUsername(String name) {
        return userRepository.findByName(name);
    }

    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean usernameExists(String name) {
        return userRepository.existsByName(name);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

}

