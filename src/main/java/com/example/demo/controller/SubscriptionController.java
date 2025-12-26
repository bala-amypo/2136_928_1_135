// package com.example.demo.controller;

// import com.example.demo.entity.Subscription;
// import com.example.demo.service.SubscriptionService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/subscriptions")
// public class SubscriptionController {

//     private final SubscriptionService subscriptionService;

//     public SubscriptionController(SubscriptionService subscriptionService) {
//         this.subscriptionService = subscriptionService;
//     }

//     @PostMapping("/{eventId}")
//     public ResponseEntity<Subscription> subscribe(
//             @RequestParam Long userId,
//             @PathVariable Long eventId) {

//         return ResponseEntity.ok(subscriptionService.subscribe(userId, eventId));
//     }

//     @DeleteMapping("/{eventId}")
//     public ResponseEntity<Void> unsubscribe(
//             @RequestParam Long userId,
//             @PathVariable Long eventId) {

//         subscriptionService.unsubscribe(userId, eventId);
//         return ResponseEntity.ok().build();
//     }

//     @GetMapping("/user/{userId}")
//     public ResponseEntity<List<Subscription>> getUserSubs(@PathVariable Long userId) {
//         return ResponseEntity.ok(subscriptionService.getSubscriptionsForUser(userId));
//     }

//     @GetMapping("/check/{userId}/{eventId}")
//     public ResponseEntity<Boolean> check(@PathVariable Long userId, @PathVariable Long eventId) {
//         return ResponseEntity.ok(subscriptionService.checkSubscription(userId, eventId));
//     }
// }
package com.example.demo.controller;

import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/{eventId}")
    public ResponseEntity<Subscription> subscribe(@RequestParam Long userId, @PathVariable Long eventId) {
        // Test cases verify that duplicates throw "Already subscribed"[cite: 58, 139].
        return ResponseEntity.status(201).body(subscriptionService.subscribe(userId, eventId));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> unsubscribe(@RequestParam Long userId, @PathVariable Long eventId) {
        subscriptionService.unsubscribe(userId, eventId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(userId));
    }

    @GetMapping("/check/{userId}/{eventId}")
    public ResponseEntity<Boolean> checkSubscription(@PathVariable Long userId, @PathVariable Long eventId) {
        return ResponseEntity.ok(subscriptionService.isSubscribed(userId, eventId));
    }
}