// package com.example.demo.service;

// import com.example.demo.entity.EventUpdate;
// import java.util.List;

// public interface EventUpdateService {

//     // Create a new event update
//     EventUpdate createEventUpdate(EventUpdate eventUpdate);

//     // Get update by ID
//     EventUpdate getUpdateById(long id);

//     // Get all updates for a specific event, ordered by timestamp
//     List<EventUpdate> getUpdatesByEventId(long eventId);

//     // Update an existing event update
//     EventUpdate updateEventUpdate(EventUpdate eventUpdate);

//     // Delete an event update
//     void deleteEventUpdate(long id);

//     // Methods for controller compatibility
//     EventUpdate publishUpdate(EventUpdate update);
//     List<EventUpdate> getUpdatesForEvent(Long eventId);

//     // Optional: record delivery (throw UnsupportedOperationException if BroadcastService doesn't support it)
//     void recordDelivery(long userId, long eventUpdateId, boolean success);
// }
package com.example.demo.service;

import com.example.demo.entity.EventUpdate;
import java.util.List;

public interface EventUpdateService {
    List<EventUpdate> getUpdatesForEvent(Long eventId);
}