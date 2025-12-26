// package com.example.demo.service;

// import com.example.demo.entity.Event;
// import java.util.List;

// public interface EventService {

//     Event createEvent(Event event);

//     Event updateEvent(Long id, Event event);

//     // Existing method
//     Event getEventById(Long id);

//     // ✅ REQUIRED by test cases
//     Event getById(long id);

//     List<Event> getActiveEvents();

//     void deactivateEvent(Long id);
// }


package com.example.demo.service;

import com.example.demo.entity.Event;
import java.util.List;

public interface EventService {
    
    // Core CRUD methods
    Event createEvent(Event event);
    
    Event getById(Long id);
    
    Event updateEvent(Long id, Event eventDetails);
    
    void deactivateEvent(Long id);

    // Filter and Search methods (Required by your test file)
    List<Event> getActiveEvents();
    
    List<Event> getEventsByCategory(String category);
    
    List<Event> searchEventsByLocation(String location);
}