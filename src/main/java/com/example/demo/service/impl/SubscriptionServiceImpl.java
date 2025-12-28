
package com.example.demo.service.impl;

import com.example.demo.entity.Subscription;
import com.example.demo.entity.User;
import com.example.demo.entity.Event;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.SubscriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subRepo, UserRepository userRepo, EventRepository eventRepo) {
        this.subscriptionRepository = subRepo;
        this.userRepository = userRepo;
        this.eventRepository = eventRepo;
    }

    @Override
    public Subscription subscribe(Long userId, Long eventId) {
        if (subscriptionRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new BadRequestException("Already subscribed");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        Subscription sub = new Subscription();
        sub.setUser(user);
        sub.setEvent(event);
        return subscriptionRepository.save(sub);
    }

    @Override
    public void unsubscribe(Long userId, Long eventId) {
        Subscription s = subscriptionRepository.findByUserIdAndEventId(userId, eventId)
                .orElseThrow(() -> new BadRequestException("Subscription not found"));
        subscriptionRepository.delete(s);
    }

    @Override public boolean isSubscribed(Long userId, Long eventId) { return subscriptionRepository.existsByUserIdAndEventId(userId, eventId); }
    @Override public List<Subscription> getUserSubscriptions(Long userId) { return subscriptionRepository.findByUserId(userId); }
    @Override public List<Subscription> getSubscriptionsForUser(Long userId) { return getUserSubscriptions(userId); }
    @Override public boolean checkSubscription(Long userId, Long eventId) { return isSubscribed(userId, eventId); }
}
