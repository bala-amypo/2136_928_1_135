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
    public EventUpdate save(Long eventId, EventUpdate update) {  
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        update.setEvent(event);
        return eventUpdateRepository.save(update);
    }

    @Override
    public List<EventUpdate> getAll() {
        return eventUpdateRepository.findAll();
    }

    @Override
    public EventUpdate getById(Long id) {  
        return eventUpdateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Update not found"));
    }

    @Override
    public void delete(Long id) {  
        eventUpdateRepository.delete(getById(id));
    }
}
