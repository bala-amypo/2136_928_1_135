package com.example.demo.controller;

import com.example.demo.dto.EventUpdateRequest;
import com.example.demo.entity.Event;
import com.example.demo.entity.EventUpdate;
import com.example.demo.service.EventService;
import com.example.demo.service.EventUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/updates")
public class EventUpdateController {

    private final EventUpdateService updateService;
    private final EventService eventService;

    public EventUpdateController(EventUpdateService updateService, EventService eventService) {
        this.updateService = updateService;
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventUpdate> publish(@RequestBody EventUpdateRequest request) {
        Event event = eventService.getEventById(request.getEventId());

        EventUpdate update = new EventUpdate();
        update.setEvent(event);
        update.setUpdateContent(request.getUpdateContent());
        update.setUpdateType(request.getUpdateType());

        return ResponseEntity.ok(updateService.publishUpdate(update));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<EventUpdate>> getByEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(updateService.getUpdatesForEvent(eventId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventUpdate> getById(@PathVariable Long id) {
        return ResponseEntity.ok(updateService.getUpdateById(id));
    }
}
