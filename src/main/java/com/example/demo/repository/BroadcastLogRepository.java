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

public interface BroadcastLogRepository {
    List<BroadcastLog> findByEventUpdateId(Long updateId);
    
    // The test requires this method to exist in this interface
    BroadcastLog save(BroadcastLog log);
}