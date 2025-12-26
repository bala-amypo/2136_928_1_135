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
    
    // This EXACT signature is required to stop the "ambiguous" error 
    // and satisfy the Mockito call: Mockito.any(BroadcastLog.class)
    <S extends BroadcastLog> S save(S entity);
}