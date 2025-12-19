package com.example.demo.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.example.demo.entity.EventUpdate;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {

    private Map<Integer, EventUpdate> updates = new HashMap<>();

    @Override
    public EventUpdate save(EventUpdate update) {
        updates.put(update.getId(), update);
        return update;
    }

    @Override
    public List<EventUpdate> getAll() {
        return new ArrayList<>(updates.values());
    }

    @Override
    public Optional<EventUpdate> getById(Integer id) {
        return Optional.ofNullable(updates.get(id));
    }

    @Override
    public void delete(Integer id) {
        updates.remove(id);
    }
}
