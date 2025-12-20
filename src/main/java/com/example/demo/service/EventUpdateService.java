package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.EventUpdate;

public interface EventUpdateService {
    EventUpdate save(EventUpdate update);
    List<EventUpdate> getAll();
    EventUpdate getById(Integer id);  // Return EventUpdate directly
    void delete(Integer id);
}
