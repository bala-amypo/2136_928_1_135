
package com.example.demo.service.impl;

import com.example.demo.entity.EventUpdate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.EventUpdateService;
import com.example.demo.service.BroadcastService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EventUpdateServiceImpl implements EventUpdateService {
    private final EventUpdateRepository eventUpdateRepository;
    private final BroadcastService broadcastService;

    public EventUpdateServiceImpl(EventUpdateRepository repo, BroadcastService broadcastService) {
        this.eventUpdateRepository = repo;
        this.broadcastService = broadcastService;
    }

    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        EventUpdate saved = eventUpdateRepository.save(update);
        // Trigger notification using the saved ID
        broadcastService.broadcastUpdate(saved.getId()); 
        return saved;
    }

    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return eventUpdateRepository.findByEventIdOrderByTimestampAsc(eventId);
    }

    @Override
    public EventUpdate getUpdateById(Long id) {
        return eventUpdateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Update not found"));
    }
}