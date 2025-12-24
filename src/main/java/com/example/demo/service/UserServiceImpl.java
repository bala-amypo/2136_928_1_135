package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.exception.OperationNotAllowedException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // -------------------- REGISTER --------------------
    @Transactional
    @Override
    public User register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.save(user);
    }

    // -------------------- GET ALL USERS --------------------
    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // -------------------- GET USER BY ID --------------------
    @Transactional(readOnly = true)
    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(
                userRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found"))
        );
    }

    // -------------------- UPDATE USER --------------------
    @Transactional
    @Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setRole(updatedUser.getRole());
        // Optionally update password if provided
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(updatedUser.getPassword());
        }

        return userRepository.save(existingUser);
    }

    // -------------------- DELETE USER --------------------
    @Transactional
    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Enforce RESTRICT referential integrity
        if ((user.getPublishedEvents() != null && !user.getPublishedEvents().isEmpty()) ||
            (user.getSubscriptions() != null && !user.getSubscriptions().isEmpty()) ||
            (user.getBroadcastLogs() != null && !user.getBroadcastLogs().isEmpty())) {
            throw new OperationNotAllowedException(
                "Cannot delete user with existing events, subscriptions, or broadcast logs"
            );
        }

        userRepository.delete(user);
    }
}
