
package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.status(201).body(eventService.createEvent(event));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    @GetMapping("/active")
    public ResponseEntity<List<Event>> getActiveEvents() {
        return ResponseEntity.ok(eventService.getActiveEvents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    @PatchMapping("/{id}/deactivate") // Fixed annotation
    public ResponseEntity<Void> deactivateEvent(@PathVariable Long id) {
        eventService.deactivateEvent(id);
        return ResponseEntity.ok().build();
    }
}






