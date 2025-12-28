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





// package com.example.demo.service.impl;

// import com.example.demo.entity.Event;
// import com.example.demo.entity.User;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.EventRepository;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.EventService;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import java.util.List;

// @Service
// @Transactional
// public class EventServiceImpl implements EventService {
    
//     private final EventRepository eventRepository;
//     private final UserRepository userRepository; // Added to fix 500 errors and test errors

//     // Constructor updated to take 2 arguments (matching your Test file requirement)
//     public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
//         this.eventRepository = eventRepository;
//         this.userRepository = userRepository;
//     }

//     @Override
//     public Event createEvent(Event event) {
//         // Fix for 500 error: Ensure the publisher exists before saving
//         if (event.getPublisher() != null && event.getPublisher().getId() != null) {
//             User publisher = userRepository.findById(event.getPublisher().getId())
//                     .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + event.getPublisher().getId()));
//             event.setPublisher(publisher);
//         }
//         return eventRepository.save(event);
//     }

//     @Override
//     public Event getById(Long id) {
//         return eventRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
//     }

//     @Override
//     public List<Event> getActiveEvents() {
//         return eventRepository.findByIsActiveTrue();
//     }

//     // New methods to fix compilation errors in DigitalLocalEventBroadcastingApiTest
//     public List<Event> getEventsByCategory(String category) {
//         return eventRepository.findByIsActiveTrueAndCategory(category);
//     }

//     public List<Event> searchEventsByLocation(String location) {
//         return eventRepository.findByIsActiveTrueAndLocationContainingIgnoreCase(location);
//     }

//     @Override
//     public Event updateEvent(Long id, Event eventDetails) {
//         Event event = getById(id);
//         event.setTitle(eventDetails.getTitle());
//         event.setDescription(eventDetails.getDescription());
//         event.setLocation(eventDetails.getLocation());
//         event.setCategory(eventDetails.getCategory()); // Added category update
//         return eventRepository.save(event);
//     }

//     @Override
//     public void deactivateEvent(Long id) {
//         Event event = getById(id);
//         event.setActive(false);
//         eventRepository.save(event);
//     }
// }








package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {
    
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

   @Override
public Event createEvent(Event event) {
    // 1. Check if publisher info is present
    if (event.getPublisher() != null && event.getPublisher().getId() != null) {
        
        // 2. Fetch the publisher (Required to pass testEventServiceUsesUserRepo)
        User publisher = userRepository.findById(event.getPublisher().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + event.getPublisher().getId()));

        // 3. THE FIX: Convert Role to String before comparison
        // We call .name() or .toString() so we can use equalsIgnoreCase()
        if (publisher.getRole() != null && publisher.getRole().toString().equalsIgnoreCase("SUBSCRIBER")) {
            throw new BadRequestException("Only users with PUBLISHER role can create events");
        }
        
        event.setPublisher(publisher);
    }
    return eventRepository.save(event);
}

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
    }

    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue();
    }

    // Support for category filtering tests
    public List<Event> getEventsByCategory(String category) {
        return eventRepository.findByIsActiveTrueAndCategory(category);
    }

    // Support for location search tests
    public List<Event> searchEventsByLocation(String location) {
        return eventRepository.findByIsActiveTrueAndLocationContainingIgnoreCase(location);
    }

    @Override
    public Event updateEvent(Long id, Event eventDetails) {
        Event event = getById(id);
        event.setTitle(eventDetails.getTitle());
        event.setDescription(eventDetails.getDescription());
        event.setLocation(eventDetails.getLocation());
        event.setCategory(eventDetails.getCategory()); 
        return eventRepository.save(event);
    }

    @Override
    public void deactivateEvent(Long id) {
        Event event = getById(id);
        event.setActive(false);
        eventRepository.save(event);
    }
}