package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.EventUpdate;

public interface EventUpdateService {
    EventUpdate getEventById(Long id);                // for controller
    EventUpdate publishUpdate(EventUpdate update);    // for controller
    List<EventUpdate> getUpdatesForEvent(Long eventId);
    EventUpdate getUpdateById(Long id);
    List<EventUpdate> getAllUpdates();
}
