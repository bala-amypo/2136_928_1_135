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
package com.example.demo.service;

import com.example.demo.entity.BroadcastLog;
import java.util.List;

public interface BroadcastService {
    void broadcastUpdate(Long updateId);
    List<BroadcastLog> getLogsForUpdate(Long updateId);
    void recordDelivery(Long updateId, Long subscriberId, boolean successful);
}