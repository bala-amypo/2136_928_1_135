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
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    
    List<Event> findByIsActiveTrue();
    
    List<Event> findByPublisherId(Long publisherId);

    // ADD THESE TWO METHODS TO FIX THE COMPILATION ERRORS:
    
    // Fixes error at test lines 717 & 719
    List<Event> findByIsActiveTrueAndCategory(String category);

    // Fixes error at test lines 730 & 733
    List<Event> findByIsActiveTrueAndLocationContainingIgnoreCase(String location);
}