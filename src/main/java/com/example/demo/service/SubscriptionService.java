package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Subscription;

public interface SubscriptionService {

    Subscription subscribe(Long userId, Long eventId);

    void unsubscribe(Long userId, Long eventId);

    List<Subscription> getUserSubscriptions(Long userId);

    boolean isSubscribed(Long userId, Long eventId);

    List<Subscription> getAllSubscriptions();
}
