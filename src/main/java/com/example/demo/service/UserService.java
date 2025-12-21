package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.User;

public interface UserService {

    User save(User user);

    List<User> getAll();

    User getById(Long id);     

    void delete(Long id);      
}
