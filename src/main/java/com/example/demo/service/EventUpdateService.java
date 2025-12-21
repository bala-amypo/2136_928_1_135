package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.EventUpdate;

public interface EventUpdateService {
    EventUpdate save(Long eventId, EventUpdate update);  // changed from Integer to Long
    List<EventUpdate> getAll();
    EventUpdate getById(Long id);  // changed from Integer to Long
    void delete(Long id);           // changed from Integer to Long
}
