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
        EventUpdate savedUpdate = eventUpdateRepository.save(eventUpdate);

        // Use triggerBroadcast() from BroadcastService
        broadcastService.triggerBroadcast(savedUpdate.getId());

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
        if (eventUpdate.getId() == null) return null;
        return eventUpdateRepository.save(eventUpdate);
    }

    @Override
    public void deleteEventUpdate(long id) {
        eventUpdateRepository.deleteById(id);
    }

    // Remove recordDelivery() as BroadcastService does not provide it
    @Override
    public void recordDelivery(long userId, long eventUpdateId, boolean success) {
        throw new UnsupportedOperationException("recordDelivery not supported in current BroadcastService");
    }
}
