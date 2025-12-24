package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    // -------------------- SUBSCRIBE --------------------
    @PostMapping("/{eventId}")
    @PreAuthorize("hasRole('SUBSCRIBER')")
    public ResponseEntity<Subscription> subscribe(@RequestParam Long userId, @PathVariable Long eventId) {
        Subscription subscription = service.subscribe(userId, eventId);
        return new ResponseEntity<>(subscription, HttpStatus.CREATED);
    }

    // -------------------- UNSUBSCRIBE --------------------
    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasRole('SUBSCRIBER')")
    public ResponseEntity<Void> unsubscribe(@RequestParam Long userId, @PathVariable Long eventId) {
        service.unsubscribe(userId, eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // -------------------- LIST SUBSCRIPTIONS FOR USER --------------------
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('SUBSCRIBER') or hasRole('ADMIN')")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@PathVariable Long userId) {
        List<Subscription> subscriptions = service.getUserSubscriptions(userId);
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    // -------------------- CHECK IF SUBSCRIBED --------------------
    @GetMapping("/check/{userId}/{eventId}")
    @PreAuthorize("hasRole('SUBSCRIBER')")
    public ResponseEntity<Boolean> isSubscribed(@PathVariable Long userId, @PathVariable Long eventId) {
        boolean subscribed = service.isSubscribed(userId, eventId);
        return new ResponseEntity<>(subscribed, HttpStatus.OK);
    }

    // -------------------- LIST ALL SUBSCRIPTIONS (ADMIN ONLY) --------------------
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        List<Subscription> subscriptions = service.getAllSubscriptions();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }
}
