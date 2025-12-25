package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Event;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Event save(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Invalid event data");
        }
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
        Event event = getById(id);

        if ((event.getSubscriptions() != null && !event.getSubscriptions().isEmpty()) ||
            (event.getUpdates() != null && !event.getUpdates().isEmpty())) {
            throw new IllegalStateException("Cannot delete Event with existing subscriptions or updates");
        }

        eventRepository.delete(event);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public Event createEvent(Event event) {
        return save(event);
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        Event existing = getById(id);
        existing.setTitle(event.getTitle());
        existing.setDescription(event.getDescription());
        existing.setLocation(event.getLocation());
        existing.setCategory(event.getCategory());
        existing.setPublisher(event.getPublisher());
        return save(existing);
    }

    @Override
    public List<Event> getAllEvents() {
        return getAll();
    }

    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue();
    }

    @Override
    public void deactivateEvent(Long id) {
        Event event = getById(id);
        event.setActive(false);
        save(event);
    }
}
