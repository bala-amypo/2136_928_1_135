package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    // Register a new user
    User register(User user);

    // Find user by ID
    User findById(long id);
}
