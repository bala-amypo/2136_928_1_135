package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.EventRequest;
import com.example.demo.entity.Event;
import com.example.demo.service.EventService;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    // -------------------- CREATE EVENT --------------------
    @PostMapping
    @PreAuthorize("hasRole('PUBLISHER') or hasRole('ADMIN')")
    public ResponseEntity<Event> createEvent(@RequestBody EventRequest request) {
        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setLocation(request.getLocation());
        event.setCategory(request.getCategory());
        event.setPublisher(service.getUserById(request.getPublisherId())); // Fetch User entity

        Event savedEvent = service.createEvent(event);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    // -------------------- UPDATE EVENT --------------------
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PUBLISHER') or hasRole('ADMIN')")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody EventRequest request) {
        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setLocation(request.getLocation());
        event.setCategory(request.getCategory());
        event.setPublisher(service.getUserById(request.getPublisherId()));

        Event updatedEvent = service.updateEvent(id, event);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    // -------------------- GET EVENT BY ID --------------------
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('PUBLISHER') or hasRole('ADMIN') or hasRole('SUBSCRIBER')")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = service.getById(id); // Throws exception if not found
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    // -------------------- GET ALL EVENTS --------------------
    @GetMapping
    @PreAuthorize("hasRole('PUBLISHER') or hasRole('ADMIN') or hasRole('SUBSCRIBER')")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = service.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // -------------------- GET ALL ACTIVE EVENTS --------------------
    @GetMapping("/active")
    @PreAuthorize("hasRole('PUBLISHER') or hasRole('ADMIN') or hasRole('SUBSCRIBER')")
    public ResponseEntity<List<Event>> getActiveEvents() {
        List<Event> activeEvents = service.getActiveEvents();
        return new ResponseEntity<>(activeEvents, HttpStatus.OK);
    }

    // -------------------- DEACTIVATE EVENT --------------------
    @PatchMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('PUBLISHER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deactivateEvent(@PathVariable Long id) {
        service.deactivateEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
