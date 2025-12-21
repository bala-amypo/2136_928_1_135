package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Event;
import com.example.demo.entity.Subscription;
import com.example.demo.entity.User;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.repository.UserRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   UserRepository userRepository,
                                   EventRepository eventRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Subscription subscribe(Long userId, Long eventId) {

        if (subscriptionRepository.existsByUser_IdAndEvent_Id(userId, eventId)) {
            throw new IllegalArgumentException("Already subscribed");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Subscription sub = new Subscription(user, event);
        return subscriptionRepository.save(sub);
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }
}
