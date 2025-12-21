package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Subscription;

public interface SubscriptionService {

    Subscription subscribe(Long userId, Long eventId);

    List<Subscription> getAll();
}
