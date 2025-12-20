package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.entity.User;

public interface UserService {

    User save(User user);

    List<User> getAll();          // ✅ ADD
    User getById(Integer id); // ✅ ADD

    void delete(Integer id);
}
