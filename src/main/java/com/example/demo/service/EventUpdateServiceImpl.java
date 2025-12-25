package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Event;
import com.example.demo.entity.EventUpdate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.EventUpdateRepository;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {

    @Autowired
    private EventUpdateRepository eventUpdateRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public EventUpdate getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"))
                .getUpdates()
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No updates found for event"));
    }

    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        Long eventId = update.getEvent().getId();
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        update.setEvent(event);
        return eventUpdateRepository.save(update);
    }

    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        return event.getUpdates();
    }

    @Override
    public EventUpdate getUpdateById(Long id) {
        return eventUpdateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event update not found"));
    }

    @Override
    public List<EventUpdate> getAllUpdates() {
        return eventUpdateRepository.findAll();
    }
}
