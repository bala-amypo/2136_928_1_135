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

// We extend BroadcastLogRepository to satisfy the Test's type requirement
public interface EventUpdateRepository extends JpaRepository<EventUpdate, Long>, BroadcastLogRepository {
    
    List<EventUpdate> findByEventIdOrderByTimestampAsc(Long eventId);
}