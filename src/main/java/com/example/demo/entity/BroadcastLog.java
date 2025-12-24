package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "broadcast_logs")
public class BroadcastLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ EventUpdate reference is required (RESTRICT)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_update_id", nullable = false)
    private EventUpdate eventUpdate;

    // ✅ Subscriber reference is required (RESTRICT)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id", nullable = false)
    private User subscriber;

    @Column(nullable = false)
    private String deliveryStatus = "SENT"; // default

    @Column(nullable = false, updatable = false)
    private LocalDateTime sentAt;

    // Constructors
    public BroadcastLog() {}

    public BroadcastLog(EventUpdate eventUpdate, User subscriber, String deliveryStatus) {
        this.eventUpdate = eventUpdate;
        this.subscriber = subscriber;
        this.deliveryStatus = deliveryStatus;
        this.sentAt = LocalDateTime.now();
    }

    // Automatically set sentAt on persist
    @PrePersist
    protected void onCreate() {
        if (this.sentAt == null) this.sentAt = LocalDateTime.now();
        if (this.deliveryStatus == null) this.deliveryStatus = "SENT";
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventUpdate getEventUpdate() {
        return eventUpdate;
    }

    public void setEventUpdate(EventUpdate eventUpdate) {
        this.eventUpdate = eventUpdate;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
