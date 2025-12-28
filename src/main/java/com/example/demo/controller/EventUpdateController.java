
package com.example.demo.controller;

import com.example.demo.entity.EventUpdate;
import com.example.demo.service.EventUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/updates")
public class EventUpdateController {
    private final EventUpdateService eventUpdateService;

    public EventUpdateController(EventUpdateService eventUpdateService) {
        this.eventUpdateService = eventUpdateService;
    }

    @PostMapping("/")
    public ResponseEntity<EventUpdate> publishUpdate(@RequestBody EventUpdate update) {
        
        return ResponseEntity.status(201).body(eventUpdateService.publishUpdate(update));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<EventUpdate>> getUpdatesForEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventUpdateService.getUpdatesForEvent(eventId));
    }
}