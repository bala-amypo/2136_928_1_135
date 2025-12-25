package com.example.demo.repository;

import com.example.demo.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByEventId(Long eventId);

    List<Subscription> findByUserId(Long userId);

    boolean existsByUserIdAndEventId(Long userId, Long eventId);

    Optional<Subscription> findByUserIdAndEventId(Long userId, Long eventId);
}
