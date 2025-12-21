package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(
    name = "subscriptions",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "event_id"})
    }
)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many subscriptions -> one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Many subscriptions -> one event
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    private Timestamp subscribedAt;

    // Auto-generate subscribedAt
    @PrePersist
    protected void onCreate() {
        this.subscribedAt = new Timestamp(System.currentTimeMillis());
    }

    // Constructors
    public Subscription() {}

    public Subscription(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
 
    public Event getEvent() {
        return event;
    }
 
    public void setEvent(Event event) {
        this.event = event;
    }

    public Timestamp getSubscribedAt() {
        return subscribedAt;
    }
}
