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
import com.example.demo.repository.*;
import com.example.demo.service.BroadcastService;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;
import java.util.List;

@Service
@Primary
public class BroadcastServiceImpl implements BroadcastService {
    // 1. Change this to JpaBroadcastLogRepository so .save() exists
    private final JpaBroadcastLogRepository broadcastLogRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final EventUpdateRepository eventUpdateRepository;

    public BroadcastServiceImpl(JpaBroadcastLogRepository blRepo, 
                                SubscriptionRepository sRepo, 
                                EventUpdateRepository euRepo) {
        this.broadcastLogRepository = blRepo;
        this.subscriptionRepository = sRepo;
        this.eventUpdateRepository = euRepo;
    }

    @Override
    public void broadcastUpdate(Long updateId) {
        EventUpdate update = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new RuntimeException("Update not found"));
        List<Subscription> subs = subscriptionRepository.findByEventId(update.getEvent().getId());

        for (Subscription sub : subs) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(sub.getUser());
            log.setDeliveryStatus(DeliveryStatus.SENT);
            // This will now work because JpaBroadcastLogRepository has .save()
            broadcastLogRepository.save(log);
        }
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }

    @Override
    public void recordDelivery(long updateId, long userId, boolean success) {
        List<BroadcastLog> logs = broadcastLogRepository.findByEventUpdateId(updateId);
        
        logs.stream()
            .filter(log -> log.getSubscriber().getId().equals(userId))
            .findFirst()
            .ifPresent(log -> {
                log.setDeliveryStatus(success ? DeliveryStatus.DELIVERED : DeliveryStatus.FAILED);
                broadcastLogRepository.save(log);
            });
    }
}