package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/")
    public String home() {
        return "Digital Event Broadcasting API is Running on Port 9001!";
    }

    @GetMapping("/simple-status")
    public String status() {
        return "OK";
    }
}