package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "subscriptions",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "event_id"})
)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    private LocalDateTime subscribedAt;

    @PrePersist
    protected void onCreate() {
        subscribedAt = LocalDateTime.now();
    }

    // ===== CONSTRUCTORS =====

    public Subscription() {}

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
    
    public Event getEvent() {
        return event;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getSubscribedAt() {
        return subscribedAt;
    }
}
