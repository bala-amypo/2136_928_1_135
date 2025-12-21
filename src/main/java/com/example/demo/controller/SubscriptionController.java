package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    // Subscribe using userId and eventId
    @PostMapping("/add")
    public Subscription subscribe(@RequestParam Long userId, @RequestParam Long eventId) {
        return service.subscribe(userId, eventId);
    }

    @GetMapping("/all")
    public List<Subscription> getAll() {
        return service.getAll();
    }
}
