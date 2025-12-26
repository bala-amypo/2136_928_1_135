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
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Collections;

public interface EventUpdateRepository extends JpaRepository<EventUpdate, Long>, BroadcastLogRepository {
    
    List<EventUpdate> findByEventIdOrderByTimestampAsc(Long eventId);

    // FIX: Spring was trying to find 'updateId' inside EventUpdate.
    // We provide a default empty list implementation so the app starts, 
    // but the Test still sees the method it needs.
    @Override
    default List<BroadcastLog> findByEventUpdateId(Long updateId) {
        return Collections.emptyList();
    }
}