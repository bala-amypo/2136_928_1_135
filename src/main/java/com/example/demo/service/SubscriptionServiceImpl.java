package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Subscription;
import com.example.demo.repository.SubscriptionRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription subscribe(Subscription sub) {

        if (subscriptionRepository.existsByUserIdAndEventId(
                sub.getUserId(), sub.getEventId())) {

            throw new IllegalArgumentException("Already subscribed");
        }

        return subscriptionRepository.save(sub);
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }
}
