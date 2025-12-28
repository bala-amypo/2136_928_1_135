
package com.example.demo.repository;

import com.example.demo.entity.Event;
import com.example.demo.service.BroadcastService; 
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long>, BroadcastService {
    
    List<Event> findByIsActiveTrue();
    
    List<Event> findByPublisherId(Long publisherId);

    List<Event> findByIsActiveTrueAndCategory(String category);

    List<Event> findByIsActiveTrueAndLocationContainingIgnoreCase(String location);
}