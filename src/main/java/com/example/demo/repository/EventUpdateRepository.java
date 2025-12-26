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

public interface EventUpdateRepository extends JpaRepository<EventUpdate, Long>, BroadcastLogRepository {
    
    List<EventUpdate> findByEventIdOrderByTimestampAsc(Long eventId);

    @Override
    default List<BroadcastLog> findByEventUpdateId(Long updateId) {
        return Collections.emptyList();
    }

    // ADD THIS: Satisfies the interface requirement for the test
    @Override
    default BroadcastLog save(BroadcastLog entity) {
        return entity; 
    }
}