package com.example.demo.repository;

import com.example.demo.entity.EventUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventUpdateRepository extends JpaRepository<EventUpdate, Long> {

    // Fetch all updates for a specific event, ordered by timestamp
    List<EventUpdate> findByEventIdOrderByTimestampAsc(Long eventId);
}
