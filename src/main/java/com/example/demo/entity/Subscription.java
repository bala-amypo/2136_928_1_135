// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.Instant;

// @Entity
// @Table(
//     name = "subscriptions",
//     uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "event_id"})
// )
// public class Subscription {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "user_id", nullable = false)
//     private User user;

//     @ManyToOne
//     @JoinColumn(name = "event_id", nullable = false)
//     private Event event;

//     // ✅ FIX: Instant instead of LocalDateTime
//     @Column(nullable = false)
//     private Instant subscribedAt;

//     @PrePersist
//     public void onCreate() {
//         subscribedAt = Instant.now();
//     }

//     // ===== CONSTRUCTORS =====
//     public Subscription() {}

//     // Constructor for tests
//     public Subscription(User user, Event event) {
//         this.user = user;
//         this.event = event;
//         this.subscribedAt = Instant.now();
//     }

//     // ===== GETTERS & SETTERS =====
//     public Long getId() { return id; }
//     public User getUser() { return user; }
//     public Event getEvent() { return event; }
//     public Instant getSubscribedAt() { return subscribedAt; }

//     public void setId(Long id) { this.id = id; }
//     public void setUser(User user) { this.user = user; }
//     public void setEvent(Event event) { this.event = event; }
//     public void setSubscribedAt(Instant subscribedAt) { this.subscribedAt = subscribedAt; }
// }
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "event_id"})})
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private LocalDateTime subscribedAt;

    public Subscription() {}

    @PrePersist
    public void onCreate() {
        this.subscribedAt = LocalDateTime.now(); // Auto-populated [cite: 130]
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    public LocalDateTime getSubscribedAt() { return subscribedAt; }
}