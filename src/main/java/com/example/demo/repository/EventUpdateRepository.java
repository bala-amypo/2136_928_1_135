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

    // FIX: Match the generic signature for the compiler
    @Override
    default <S extends BroadcastLog> S save(S entity) {
        return entity; 
    }
}