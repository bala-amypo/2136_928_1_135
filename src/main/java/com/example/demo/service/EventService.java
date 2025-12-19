package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.entity.Event;

public interface EventService {
    Event save(Event event);
    List<Event> getAll();
    Optional<Event> getById(Integer id);
    void delete(Integer id);
}
