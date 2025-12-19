package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    boolean existsByUserIdAndEventId(Integer userId, Integer eventId);
}
