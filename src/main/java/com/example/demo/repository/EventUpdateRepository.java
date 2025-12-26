// package com.example.demo.repository;

// import com.example.demo.entity.EventUpdate;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;

// public interface EventUpdateRepository extends JpaRepository<EventUpdate, Long> {
//     List<EventUpdate> findByEventIdOrderByTimestampAsc(Long eventId);
// }

package com.example.demo.repository;

import com.example.demo.entity.EventUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// 1. Remove "extends BroadcastLogRepository" from here 
// because it causes the JpaRepository conflict.
public interface EventUpdateRepository extends JpaRepository<EventUpdate, Long> {
    
    List<EventUpdate> findByEventIdOrderByTimestampAsc(Long eventId);
}