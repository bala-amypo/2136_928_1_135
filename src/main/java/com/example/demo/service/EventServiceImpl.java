package com.example.demo.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Event;

@Service
public class EventServiceImpl implements EventService {

    Map<Integer, Event> events = new HashMap<>();

    public Event save(Event event) {
        events.put(event.getId(), event);
        return event;
    }

    public List<Event> getAll() {
        return new ArrayList<>(events.values());
    }

    public Optional<Event> getById(Integer id) {
        return Optional.ofNullable(events.get(id));
    }

    public void delete(Integer id) {
        events.remove(id);
    }
}
