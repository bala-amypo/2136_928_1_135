package com.example.demo.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;

@Service
public class UserServiceImpl implements UserService {

    Map<Integer, User> users = new HashMap<>();

    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    public Optional<User> getById(Integer id) {
        return Optional.ofNullable(users.get(id));
    }

    public void delete(Integer id) {
        users.remove(id);
    }
}
