package com.example.demo.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Event;
import com.example.demo.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService service;

    @PostMapping("/add")
    public Event add(@RequestBody Event event) {
        return service.save(event);
    }

    @GetMapping("/all")
    public List<Event> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "Event deleted";
    }
}
