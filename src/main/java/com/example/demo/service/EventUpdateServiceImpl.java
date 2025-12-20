package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EventUpdate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventUpdateRepository;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {

    @Autowired
    private EventUpdateRepository eventUpdateRepository;

    @Override
    public EventUpdate save(EventUpdate update) {
        return eventUpdateRepository.save(update);
    }

    @Override
    public List<EventUpdate> getAll() {
        return eventUpdateRepository.findAll();
    }

    @Override
    public EventUpdate getById(Integer id) {
        return eventUpdateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event update not found"));
    }

    @Override
    public void delete(Integer id) {
        EventUpdate existing = getById(id);  // Ensure it exists, otherwise throws exception
        eventUpdateRepository.delete(existing);
    }
}
