
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.BroadcastService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Primary
@Transactional
public class BroadcastServiceImpl implements BroadcastService {

    private EventUpdateRepository eventUpdateRepository;
    private SubscriptionRepository subscriptionRepository;
    private BroadcastLogRepository broadcastLogRepository;

    
    public BroadcastServiceImpl() {}

    
    public BroadcastServiceImpl(
            EventUpdateRepository eventUpdateRepository,
            SubscriptionRepository subscriptionRepository,
            BroadcastLogRepository broadcastLogRepository) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.broadcastLogRepository = broadcastLogRepository;
    }

    
    public void triggerBroadcast(Long updateId) {
        EventUpdate update = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new ResourceNotFoundException("Update not found"));

        List<Subscription> subscriptions = subscriptionRepository.findByEventId(update.getEvent().getId());

        for (Subscription sub : subscriptions) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(sub.getUser());
            log.setDeliveryStatus(DeliveryStatus.SENT);
            broadcastLogRepository.save(log);
        }
    }

    @Override
    public void broadcastUpdate(Long updateId) {
        triggerBroadcast(updateId);
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }

    @Override
    public void recordDelivery(long updateId, long userId, boolean success) {
        List<BroadcastLog> logs = broadcastLogRepository.findByEventUpdateId(updateId);
        
        BroadcastLog log = logs.stream()
            .filter(l -> l.getSubscriber().getId().equals(userId))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Log not found"));

        log.setDeliveryStatus(success ? DeliveryStatus.DELIVERED : DeliveryStatus.FAILED);
        broadcastLogRepository.save(log);
    }
}