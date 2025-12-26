// package com.example.demo.repository;

// import com.example.demo.entity.BroadcastLog;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;

// public interface BroadcastLogRepository extends JpaRepository<BroadcastLog, Long> {
//     List<BroadcastLog> findByEventUpdateId(Long eventUpdateId);
// }


package com.example.demo.repository;

import com.example.demo.entity.BroadcastLog;
import java.util.List;

/**
 * Base interface to satisfy the Test's type requirements.
 * We removed JpaRepository from here to prevent the "different arguments" error.
 */
public interface BroadcastLogRepository {
    List<BroadcastLog> findByEventUpdateId(Long updateId);
}