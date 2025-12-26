package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserService {

    // Register a new user
    User register(User user);
    User registerUser(User user);

    // Find user by email
    User findByEmail(String email);

    // Get user by ID
    User getUserById(Long id);

    // Get all users
    List<User> getAllUsers();
}
