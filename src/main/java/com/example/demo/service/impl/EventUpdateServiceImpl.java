package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.EventUpdate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.BroadcastService;
import com.example.demo.service.EventUpdateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {

    private final EventUpdateRepository eventUpdateRepository;
    private final EventRepository eventRepository;
    private final BroadcastService broadcastService;

    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository,
                                  EventRepository eventRepository,
                                  BroadcastService broadcastService) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = eventRepository;
        this.broadcastService = broadcastService;
    }

    @Override
    public EventUpdate publishUpdate(EventUpdate update) {

        Long eventId = update.getEvent().getId();

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found"));

        update.setEvent(event);

        EventUpdate saved = eventUpdateRepository.save(update);

        broadcastService.triggerBroadcast(saved.getId());

        return saved;
    }

    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return eventUpdateRepository.findByEventId(eventId);
    }

    @Override
    public EventUpdate getUpdateById(Long id) {
        return eventUpdateRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Update not found"));
    }
}
