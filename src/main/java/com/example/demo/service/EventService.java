package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.entity.Event;

public interface EventService {

    Event save(Event event);

    Optional<Event> getById(Integer id);

    List<Event> getAll();

    void delete(Integer id);
}
