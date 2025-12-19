package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.entity.EventUpdate;

public interface EventUpdateService {

    EventUpdate save(EventUpdate update);

    List<EventUpdate> getAll();

    Optional<EventUpdate> getById(Integer id);

    void delete(Integer id);
}
