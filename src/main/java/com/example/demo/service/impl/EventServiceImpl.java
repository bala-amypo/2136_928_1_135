package com.example.demo.service.impl;

import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.Event;
import com.example.demo.service.EventService;

import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    // Constructor now accepts both repositories
    public EventServiceImpl(EventRepository eventRepository,
                            UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Event getById(long id) {
        return eventRepository.findById(id).orElse(null);
    }
}
