package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Event;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event save(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Invalid event data");
        }
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> getById(Long id) {

        Optional<Event> event = eventRepository.findById(id);

        if (event.isEmpty()) {
            throw new ResourceNotFoundException("Event not found");
        }

        return event;
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public void delete(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found")
                );

        eventRepository.delete(event);
    }
}
