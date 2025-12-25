package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Event;
import com.example.demo.entity.EventUpdate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.EventUpdateRepository;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {

    @Autowired
    private EventUpdateRepository updateRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        if (update == null || update.getEvent() == null) {
            throw new IllegalArgumentException("Invalid EventUpdate or Event data");
        }
        return updateRepository.save(update);
    }

    @Override
    public List<EventUpdate> getAllUpdates() {
        return updateRepository.findAll();
    }

    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return updateRepository.findByEvent_Id(eventId);
    }

    @Override
    public Optional<EventUpdate> getUpdateById(Long id) {
        return updateRepository.findById(id);
    }

    @Override
    public void deleteUpdate(Long id) {
        EventUpdate update = updateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event update not found"));
        updateRepository.delete(update);
    }

    @Override
    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }
}
