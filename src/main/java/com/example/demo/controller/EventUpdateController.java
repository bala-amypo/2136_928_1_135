package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.EventUpdate;
import com.example.demo.service.EventUpdateService;

@RestController
@RequestMapping("/event-updates")
public class EventUpdateController {

    @Autowired
    private EventUpdateService service;

    
    @PostMapping("/add")
    public EventUpdate addUpdate(@RequestParam Long eventId, @RequestBody EventUpdate update) {
        return service.save(eventId, update);
    }

    
    @GetMapping("/all")
    public List<EventUpdate> getAllUpdates() {
        return service.getAll();
    }

    
    @GetMapping("/{id}")
    public EventUpdate getUpdateById(@PathVariable Long id) {
        return service.getById(id);
    }

    
    @DeleteMapping("/{id}")
    public String deleteUpdate(@PathVariable Long id) {
        service.delete(id);
        return "Event update deleted successfully";
    }
}
