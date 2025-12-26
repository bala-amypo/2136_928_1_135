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
    /**
     * Triggers the broadcasting of a specific event update to all subscribers.
     */
    void broadcastUpdate(Long updateId);

    /**
     * Retrieves all delivery logs for a specific update.
     */
    List<BroadcastLog> getLogsForUpdate(Long updateId);

    /**
     * Updates the status of a specific delivery log.
     */
    void recordDelivery(long updateId, long userId, boolean success);
}