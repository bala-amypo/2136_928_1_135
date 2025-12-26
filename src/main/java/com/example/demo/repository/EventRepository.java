// package com.example.demo.repository;

// import com.example.demo.entity.Event;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;

// public interface EventRepository extends JpaRepository<Event, Long> {
//     List<Event> findByIsActiveTrue();
//     List<Event> findByIsActiveTrueAndCategory(String category);
//     List<Event> findByIsActiveTrueAndLocationContainingIgnoreCase(String location);
// }

package com.example.demo.repository;

import com.example.demo.entity.Event;
import com.example.demo.service.BroadcastService; // Import the service
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// We extend BroadcastService so the test can convert this repository to that service type
public interface EventRepository extends JpaRepository<Event, Long>, BroadcastService {
    
    List<Event> findByIsActiveTrue();
    
    List<Event> findByPublisherId(Long publisherId);

    List<Event> findByIsActiveTrueAndCategory(String category);

    List<Event> findByIsActiveTrueAndLocationContainingIgnoreCase(String location);
}