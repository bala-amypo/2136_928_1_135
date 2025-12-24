package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Event;

public interface EventService {

    Event save(Event event);

    Event getById(Long id);  

    List<Event> getAll();

    void delete(Long id);  
}
