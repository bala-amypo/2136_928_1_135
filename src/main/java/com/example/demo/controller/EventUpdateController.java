package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.EventUpdateRequest;
import com.example.demo.entity.EventUpdate;
import com.example.demo.service.EventUpdateService;

@RestController
@RequestMapping("/api/updates")
public class EventUpdateController {

    private final EventUpdateService service;

    public EventUpdateController(EventUpdateService service) {
        this.service = service;
    }

    // -------------------- PUBLISH NEW EVENT UPDATE --------------------
    @PostMapping
    @PreAuthorize("hasRole('PUBLISHER')")
    public ResponseEntity<EventUpdate> publishUpdate(@RequestBody EventUpdateRequest request) {
        EventUpdate update = new EventUpdate();
        update.setEvent(service.getEventById(request.getEventId())); // Fetch Event entity
        update.setUpdateContent(request.getUpdateContent());
        update.setUpdateType(request.getUpdateType());

        EventUpdate savedUpdate = service.publishUpdate(update);
        return new ResponseEntity<>(savedUpdate, HttpStatus.CREATED);
    }

    // -------------------- LIST ALL UPDATES FOR AN EVENT --------------------
    @GetMapping("/event/{eventId}")
    @PreAuthorize("hasRole('PUBLISHER') or hasRole('ADMIN') or hasRole('SUBSCRIBER')")
    public ResponseEntity<List<EventUpdate>> getUpdatesForEvent(@PathVariable Long eventId) {
        List<EventUpdate> updates = service.getUpdatesForEvent(eventId);
        return new ResponseEntity<>(updates, HttpStatus.OK);
    }

    // -------------------- GET UPDATE BY ID --------------------
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('PUBLISHER') or hasRole('ADMIN') or hasRole('SUBSCRIBER')")
    public ResponseEntity<EventUpdate> getUpdateById(@PathVariable Long id) {
        EventUpdate update = service.getUpdateById(id)
                .orElseThrow(() -> new RuntimeException("Event update not found"));
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    // -------------------- LIST ALL UPDATES --------------------
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EventUpdate>> getAllUpdates() {
        List<EventUpdate> updates = service.getAllUpdates();
        return new ResponseEntity<>(updates, HttpStatus.OK);
    }
}
