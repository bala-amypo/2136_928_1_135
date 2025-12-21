package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> getById(Long id) {  // Long here
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public void delete(Long id) {  // Long here
        eventRepository.deleteById(id);
    }
}
