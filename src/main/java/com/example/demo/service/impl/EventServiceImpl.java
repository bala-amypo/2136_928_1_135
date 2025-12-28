





package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {
    
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

   @Override
public Event createEvent(Event event) {
    
    if (event.getPublisher() != null && event.getPublisher().getId() != null) {
        User publisher = userRepository.findById(event.getPublisher().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        
        String roleStr = publisher.getRole() != null ? publisher.getRole().toString() : "";


        if (roleStr.equalsIgnoreCase("SUBSCRIBER")) {
            throw new BadRequestException("Only PUBLISHER or ADMIN can create events");
        }

        event.setPublisher(publisher);
    }
    return eventRepository.save(event);
}

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
    }

    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue();
    }

    
    public List<Event> getEventsByCategory(String category) {
        return eventRepository.findByIsActiveTrueAndCategory(category);
    }


    public List<Event> searchEventsByLocation(String location) {
        return eventRepository.findByIsActiveTrueAndLocationContainingIgnoreCase(location);
    }

    @Override
    public Event updateEvent(Long id, Event eventDetails) {
        Event event = getById(id);
        event.setTitle(eventDetails.getTitle());
        event.setDescription(eventDetails.getDescription());
        event.setLocation(eventDetails.getLocation());
        event.setCategory(eventDetails.getCategory()); 
        return eventRepository.save(event);
    }

    @Override
    public void deactivateEvent(Long id) {
        Event event = getById(id);
        event.setActive(false);
        eventRepository.save(event);
    }
}