package com.example.demo.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;

@Service
public class UserServiceImpl implements UserService {

    private Map<Integer, User> users = new HashMap<>();

    @Override
    public User save(User user) {

        // Duplicate Email Check
        for (User u : users.values()) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
        }

        users.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User getById(Integer id) {
        if (!users.containsKey(id)) {
            throw new RuntimeException("User not found");
        }
        return users.get(id);
    }

    @Override
    public void delete(Integer id) {
        if (!users.containsKey(id)) {
            throw new RuntimeException("User not found");
        }
        users.remove(id);
    }
}
