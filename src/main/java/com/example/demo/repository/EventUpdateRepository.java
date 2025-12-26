// package com.example.demo.repository;

// import com.example.demo.entity.EventUpdate;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;

// public interface EventUpdateRepository extends JpaRepository<EventUpdate, Long> {
//     List<EventUpdate> findByEventIdOrderByTimestampAsc(Long eventId);
// }

package com.example.demo.repository;

import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.BroadcastLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Collections;

public interface EventUpdateRepository extends JpaRepository<EventUpdate, Long>, JpaBroadcastLogRepository {
    
    List<EventUpdate> findByEventIdOrderByTimestampAsc(Long eventId);

    // Provide dummy implementations so Spring doesn't try to create database queries for these
    @Override
    default List<BroadcastLog> findByEventUpdateId(Long updateId) {
        return Collections.emptyList();
    }

    @Override
    default BroadcastLog save(BroadcastLog entity) {
        return entity;
    }
}