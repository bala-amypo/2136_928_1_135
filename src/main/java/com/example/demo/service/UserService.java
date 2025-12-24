package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.entity.User;

public interface UserService {

    // Register a new user
    User register(User user);

    // Get all users
    List<User> getAllUsers();

    // Find user by ID
    Optional<User> findById(Long id);

    // Update user by ID
    User updateUser(Long id, User user);

    // Delete user by ID
    void delete(Long id);
}
