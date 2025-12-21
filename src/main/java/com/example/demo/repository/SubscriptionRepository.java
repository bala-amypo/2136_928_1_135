package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Subscription;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    boolean existsByUser_IdAndEvent_Id(Long userId, Long eventId);

    List<Subscription> findByEvent_Id(Long eventId);

    List<Subscription> findByUser_Id(Long userId);
}
