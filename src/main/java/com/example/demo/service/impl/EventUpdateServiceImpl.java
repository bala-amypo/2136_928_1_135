package com.example.demo.service.impl;

import com.example.demo.entity.EventUpdate;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.EventRepository;
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
    public EventUpdate createEventUpdate(EventUpdate eventUpdate) {
        // Save the update
        EventUpdate savedUpdate = eventUpdateRepository.save(eventUpdate);

        // Broadcast the update to all subscribers of the event
        broadcastService.broadcastUpdate(savedUpdate.getEvent().getId());

        return savedUpdate;
    }

    @Override
    public EventUpdate getUpdateById(long id) {
        return eventUpdateRepository.findById(id).orElse(null);
    }

    @Override
    public List<EventUpdate> getUpdatesByEventId(long eventId) {
        return eventUpdateRepository.findByEventIdOrderByTimestampAsc(eventId);
    }

    @Override
    public EventUpdate updateEventUpdate(EventUpdate eventUpdate) {
        if (eventUpdate.getId() == null) {
            return null;
        }
        return eventUpdateRepository.save(eventUpdate);
    }

    @Override
    public void deleteEventUpdate(long id) {
        eventUpdateRepository.deleteById(id);
    }

    @Override
    public void recordDelivery(long userId, long eventUpdateId, boolean success) {
        broadcastService.recordDelivery(userId, eventUpdateId, success);
    }
}
