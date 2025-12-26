// package com.example.demo.service.impl;

// import com.example.demo.entity.Event;
// import com.example.demo.repository.EventRepository;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.EventService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class EventServiceImpl implements EventService {

//     private final EventRepository eventRepository;
//     private final UserRepository userRepository;

//     public EventServiceImpl(EventRepository eventRepository,
//                             UserRepository userRepository) {
//         this.eventRepository = eventRepository;
//         this.userRepository = userRepository;
//     }

//     @Override
//     public Event createEvent(Event event) {
//         return eventRepository.save(event);
//     }

//     @Override
//     public Event updateEvent(Long id, Event event) {
//         Event existing = eventRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Event not found"));
//         existing.setTitle(event.getTitle());
//         existing.setDescription(event.getDescription());
//         existing.setLocation(event.getLocation());
//         existing.setCategory(event.getCategory());
//         existing.setIsActive(event.getIsActive());
//         return eventRepository.save(existing);
//     }

//     @Override
//     public Event getEventById(Long id) {
//         return eventRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Event not found"));
//     }

//     // ✅ REQUIRED by test cases
//     @Override
//     public Event getById(long id) {
//         return getEventById(id);
//     }

//     @Override
//     public List<Event> getActiveEvents() {
//         return eventRepository.findByIsActiveTrue();
//     }

//     @Override
//     public void deactivateEvent(Long id) {
//         Event event = eventRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Event not found"));
//         event.setIsActive(false);
//         eventRepository.save(event);
//     }
// }


package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.EventService;
import java.util.List;

public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override public Event createEvent(Event event) {
        User pub = userRepository.findById(event.getPublisher().getId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (pub.getRole() == Role.SUBSCRIBER) throw new BadRequestException("Only PUBLISHER or ADMIN can create events");
        return eventRepository.save(event);
    }
    @Override public Event updateEvent(Long id, Event updated) {
        Event ex = getById(id);
        ex.setTitle(updated.getTitle());
        ex.setDescription(updated.getDescription());
        ex.setLocation(updated.getLocation());
        return eventRepository.save(ex);
    }
    @Override public void deactivateEvent(Long id) {
        Event e = getById(id); e.setActive(false); eventRepository.save(e);
    }
    @Override public List<Event> getActiveEvents() { return eventRepository.findByIsActiveTrue(); }
    @Override public Event getById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }
    @Override public Event getEventById(Long id) { return getById(id); }
}