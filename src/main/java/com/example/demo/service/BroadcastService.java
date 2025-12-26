// package com.example.demo.service;

// import com.example.demo.entity.BroadcastLog;

// import java.util.List;

// public interface BroadcastService {

//     // Trigger broadcast for an EventUpdate
//     void triggerBroadcast(Long updateId);

//     List<BroadcastLog> getLogsForUpdate(Long updateId);

//     // ===== Methods expected by tests =====
//     void broadcastUpdate(long updateId);

//     void recordDelivery(long updateId, long userId, boolean status);
// }
package com.example.demo.repository;

import com.example.demo.entity.BroadcastLog;
import java.util.List;

/**
 * Base interface used to satisfy the test requirements (Line 56).
 * This allows EventUpdateRepository to be cast as a BroadcastLogRepository.
 */
public interface BroadcastLogRepository {
    List<BroadcastLog> findByEventUpdateId(Long updateId);
}