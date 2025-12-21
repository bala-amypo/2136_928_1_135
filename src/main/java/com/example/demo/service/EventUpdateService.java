package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.EventUpdate;

public interface EventUpdateService {
    EventUpdate save(Long eventId, EventUpdate update);  
    List<EventUpdate> getAll();
    EventUpdate getById(Long id);  
    void delete(Long id);           
}
