package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user); // Saves to MySQL
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll(); // Fetches all users
    }

    @Override
    public Optional<User> getById(Integer id) {
        return userRepository.findById(id); // Fetch single user by ID
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id); // Deletes user
    }
}
