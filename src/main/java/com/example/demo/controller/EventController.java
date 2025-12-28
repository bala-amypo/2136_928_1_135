// package com.example.demo.controller;

// import com.example.demo.dto.EventRequest;
// import com.example.demo.entity.Event;
// import com.example.demo.entity.User;
// import com.example.demo.service.EventService;
// import com.example.demo.service.UserService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/events")
// public class EventController {

//     private final EventService eventService;
//     private final UserService userService;

//     public EventController(EventService eventService, UserService userService) {
//         this.eventService = eventService;
//         this.userService = userService;
//     }

//     @PostMapping
//     public ResponseEntity<Event> create(@RequestBody EventRequest request) {
//         User publisher = userService.getUserById(request.getPublisherId());

//         Event event = new Event();
//         event.setTitle(request.getTitle());
//         event.setDescription(request.getDescription());
//         event.setLocation(request.getLocation());
//         event.setCategory(request.getCategory());
//         event.setPublisher(publisher);

//         return ResponseEntity.ok(eventService.createEvent(event));
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody EventRequest request) {
//         Event event = new Event();
//         event.setTitle(request.getTitle());
//         event.setDescription(request.getDescription());
//         event.setLocation(request.getLocation());
//         event.setCategory(request.getCategory());

//         return ResponseEntity.ok(eventService.updateEvent(id, event));
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Event> get(@PathVariable Long id) {
//         return ResponseEntity.ok(eventService.getEventById(id));
//     }

//     @GetMapping("/active")
//     public ResponseEntity<List<Event>> getActive() {
//         return ResponseEntity.ok(eventService.getActiveEvents());
//     }

//     @PatchMapping("/{id}/deactivate")
//     public ResponseEntity<Void> deactivate(@PathVariable Long id) {
//         eventService.deactivateEvent(id);
//         return ResponseEntity.ok().build();
//     }
// }
// package com.example.demo.controller;

// import com.example.demo.entity.Event;
// import com.example.demo.service.EventService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/events")
// public class EventController {
//     private final EventService eventService;

//     public EventController(EventService eventService) {
//         this.eventService = eventService;
//     }

//     @PostMapping("/")
//     public ResponseEntity<Event> createEvent(@RequestBody Event event) {
//         return ResponseEntity.status(201).body(eventService.createEvent(event));
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Event> getEvent(@PathVariable Long id) {
//         return ResponseEntity.ok(eventService.getById(id));
//     }

//     @GetMapping("/active")
//     public ResponseEntity<List<Event>> getActiveEvents() {
//         return ResponseEntity.ok(eventService.getActiveEvents());
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
//         return ResponseEntity.ok(eventService.updateEvent(id, event));
//     }

//     @PatchMapping("/{id}/deactivate") // Fixed annotation
//     public ResponseEntity<Void> deactivateEvent(@PathVariable Long id) {
//         eventService.deactivateEvent(id);
//         return ResponseEntity.ok().build();
//     }
// }






package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping // Removed the trailing slash "/" to match standard test pathing
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        // The service now handles the role check and publisher lookup
        // We return 201 Created as expected by the test suite
        return ResponseEntity.status(201).body(eventService.createEvent(event));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    @GetMapping("/active")
    public ResponseEntity<List<Event>> getActiveEvents() {
        return ResponseEntity.ok(eventService.getActiveEvents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateEvent(@PathVariable Long id) {
        eventService.deactivateEvent(id);
        return ResponseEntity.ok().build();
    }
}