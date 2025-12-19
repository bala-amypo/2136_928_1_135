package com.example.demo.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/add")
    public User add(@RequestBody User user) {
        return service.save(user);
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<User> get(@PathVariable Integer id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "Deleted";
    }
}
