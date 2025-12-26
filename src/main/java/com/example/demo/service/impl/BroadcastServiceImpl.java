// package com.example.demo.service.impl;

// import com.example.demo.entity.*;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.*;
// import com.example.demo.service.BroadcastService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class BroadcastServiceImpl implements BroadcastService {

//     private EventUpdateRepository eventUpdateRepository;
//     private SubscriptionRepository subscriptionRepository;
//     private BroadcastLogRepository broadcastLogRepository;

//     // ✅ REQUIRED by tests (NO @Autowired)
//     public BroadcastServiceImpl() {}

//     // ✅ REQUIRED by tests (ORDER MATTERS)
//     public BroadcastServiceImpl(
//             EventUpdateRepository eventUpdateRepository,
//             SubscriptionRepository subscriptionRepository,
//             BroadcastLogRepository broadcastLogRepository) {

//         this.eventUpdateRepository = eventUpdateRepository;
//         this.subscriptionRepository = subscriptionRepository;
//         this.broadcastLogRepository = broadcastLogRepository;
//     }

//     // ✅ ONLY constructor annotated with @Autowired
//     @Autowired
//     public BroadcastServiceImpl(
//             BroadcastLogRepository broadcastLogRepository,
//             SubscriptionRepository subscriptionRepository,
//             EventUpdateRepository eventUpdateRepository) {

//         this.broadcastLogRepository = broadcastLogRepository;
//         this.subscriptionRepository = subscriptionRepository;
//         this.eventUpdateRepository = eventUpdateRepository;
//     }

//     @Override
//     public void triggerBroadcast(Long updateId) {
//         EventUpdate update = eventUpdateRepository.findById(updateId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Update not found"));

//         List<Subscription> subscriptions =
//                 subscriptionRepository.findByEventId(update.getEvent().getId());

//         for (Subscription sub : subscriptions) {
//             BroadcastLog log = new BroadcastLog();
//             log.setEventUpdate(update);
//             log.setSubscriber(sub.getUser());
//             log.setDeliveryStatus(DeliveryStatus.SENT);
//             broadcastLogRepository.save(log);
//         }
//     }

//     @Override
//     public List<BroadcastLog> getLogsForUpdate(Long updateId) {
//         return broadcastLogRepository.findByEventUpdateId(updateId);
//     }

//     // ===== Test-required methods =====

//     @Override
//     public void broadcastUpdate(long updateId) {
//         triggerBroadcast(updateId);
//     }

//     @Override
//     public void recordDelivery(long updateId, long userId, boolean status) {
//         BroadcastLog log = broadcastLogRepository
//                 .findByEventUpdateId(updateId).stream()
//                 .filter(b -> b.getSubscriber().getId().equals(userId))
//                 .findFirst()
//                 .orElseThrow(() -> new ResourceNotFoundException("Broadcast log not found"));

//         log.setDeliveryStatus(
//                 status ? DeliveryStatus.DELIVERED : DeliveryStatus.FAILED
//         );
//         broadcastLogRepository.save(log);
//     }
// }
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.BroadcastService;
import java.util.List;

public class BroadcastServiceImpl implements BroadcastService {
    private EventUpdateRepository eventUpdateRepository;
    private SubscriptionRepository subscriptionRepository;
    private BroadcastLogRepository broadcastLogRepository;

    // Constructor order matches Test Source 8
    public BroadcastServiceImpl(EventUpdateRepository eventUpdateRepository, SubscriptionRepository subscriptionRepository, BroadcastLogRepository broadcastLogRepository) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.broadcastLogRepository = broadcastLogRepository;
    }

    @Override
    public void broadcastUpdate(Long updateId) {
        EventUpdate update = eventUpdateRepository.findById(updateId).orElseThrow();
        List<Subscription> subs = subscriptionRepository.findByEventId(update.getEvent().getId());

        for (Subscription sub : subs) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(sub.getUser());
            log.setDeliveryStatus(DeliveryStatus.SENT);
            broadcastLogRepository.save(log);
        }
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }

    @Override
    public void recordDelivery(Long updateId, Long subscriberId, boolean successful) {
        // Implementation for Source 120
        List<BroadcastLog> logs = broadcastLogRepository.findByEventUpdateId(updateId);
        // Simplified logic to find specific log
        for(BroadcastLog log : logs) {
             if(log.getSubscriber().getId().equals(subscriberId)) {
                 log.setDeliveryStatus(successful ? DeliveryStatus.SENT : DeliveryStatus.FAILED);
                 broadcastLogRepository.save(log);
             }
        }
    }
}