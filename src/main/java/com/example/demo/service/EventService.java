package com.example.demo.service;

import com.example.demo.entity.Event;
import java.util.List;

public interface EventService {

    Event createEvent(Event event);

    Event updateEvent(Long id, Event event);

    Event getById(long id);  // <-- FIXED per test case

    List<Event> getActiveEvents();

    void deactivateEvent(Long id);
}
