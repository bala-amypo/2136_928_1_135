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

import com.example.demo.entity.Event;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue();
    }

    @Override
    public Event updateEvent(Long id, Event eventDetails) {
        Event event = getById(id);
        event.setTitle(eventDetails.getTitle());
        event.setDescription(eventDetails.getDescription());
        event.setLocation(eventDetails.getLocation());
        return eventRepository.save(event);
    }

    @Override
    public void deactivateEvent(Long id) {
        Event event = getById(id);
        event.setActive(false);
        eventRepository.save(event);
    }
}