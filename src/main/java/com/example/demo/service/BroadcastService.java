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

/**
 * Interface for managing event update broadcasts to subscribers.
 */
public interface BroadcastService {

    /**
     * Triggers the broadcasting of an update to all subscribed users.
     */
    void broadcastUpdate(Long updateId);

    /**
     * Retrieves all delivery logs associated with a specific update.
     */
    List<BroadcastLog> getLogsForUpdate(Long updateId);

    /**
     * Required by Test Line 793: Records the success or failure of a delivery 
     * to a specific user.
     */
    void recordDelivery(long updateId, long userId, boolean success);
}