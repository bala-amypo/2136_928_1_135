


package com.example.demo.service;

import com.example.demo.entity.Event;
import java.util.List;

public interface EventService {
    
    
    Event createEvent(Event event);
    
    Event getById(Long id);
    
    Event updateEvent(Long id, Event eventDetails);
    
    void deactivateEvent(Long id);

    
    List<Event> getActiveEvents();
    
    List<Event> getEventsByCategory(String category);
    
    List<Event> searchEventsByLocation(String location);
}