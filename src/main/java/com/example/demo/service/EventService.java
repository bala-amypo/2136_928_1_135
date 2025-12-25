package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Event;
import com.example.demo.entity.User;

public interface EventService {

    // ✅ EXISTING (keep)
    Event save(Event event);
    Event getById(Long id);
    List<Event> getAll();
    void delete(Long id);

    // 🔹 REQUIRED by EventController
    User getUserById(Long userId);

    Event createEvent(Event event);

    Event updateEvent(Long eventId, Event event);

    List<Event> getAllEvents();

    List<Event> getActiveEvents();

    void deactivateEvent(Long eventId);
}
