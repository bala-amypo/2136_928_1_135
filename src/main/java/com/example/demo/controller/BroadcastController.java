package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.BroadcastLog;
import com.example.demo.service.BroadcastService;

@RestController
@RequestMapping("/broadcasts")
public class BroadcastController {

    @Autowired
    private BroadcastService service;

    @PostMapping("/send")
    public BroadcastLog send(@RequestBody BroadcastLog log) {
        return service.save(log);
    }

    @GetMapping("/all")
    public List<BroadcastLog> getAll() {
        return service.getAll();
    }

    
    @GetMapping("/{id}")
    public BroadcastLog getById(@PathVariable Long id) {
        return service.getAll().stream()
                .filter(log -> log.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("BroadcastLog not found"));
    }
}
