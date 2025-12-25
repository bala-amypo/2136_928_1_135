package com.example.demo.service;

import com.example.demo.entity.EventUpdate;
import java.util.List;
import java.util.Optional;

public interface EventUpdateService {
    EventUpdate publishUpdate(EventUpdate update);           // Create new update
    List<EventUpdate> getAllUpdates();                       // List all updates
    List<EventUpdate> getUpdatesForEvent(Long eventId);      // List updates by event
    Optional<EventUpdate> getUpdateById(Long id);            // Get single update
    void deleteUpdate(Long id);                              
    Event getEventById(Long eventId);                        // Helper to fetch Event entity
}
