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
    SubscriptionService service;

    @PostMapping("/add")
    public Subscription subscribe(@RequestBody Subscription sub) {
        return service.subscribe(sub);
    }

    @GetMapping("/all")
    public List<Subscription> getAll() {
        return service.getAll();
    }
}
