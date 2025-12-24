package com.example.demo.service;

import java.util.List;

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

        // If updating an existing event, lastUpdatedAt will be handled by @PreUpdate in entity
        return eventRepository.save(event);
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        // Referential integrity check (RESTRICT)
        if ((event.getSubscriptions() != null && !event.getSubscriptions().isEmpty()) ||
            (event.getUpdates() != null && !event.getUpdates().isEmpty())) {
            throw new IllegalStateException(
                "Cannot delete Event with existing subscriptions or updates"
            );
        }

        eventRepository.delete(event);
    }
}
