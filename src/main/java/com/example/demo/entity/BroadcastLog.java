package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "broadcast_logs")
public class BroadcastLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_update_id", nullable = false)
    private EventUpdate eventUpdate;

    @ManyToOne
    @JoinColumn(name = "subscriber_id", nullable = false)
    private User subscriber;

    @Column(nullable = false)
    private String deliveryStatus; // PENDING / SENT / FAILED

    private LocalDateTime sentAt;

    @PrePersist
    protected void onCreate() {
        sentAt = LocalDateTime.now();
        if (deliveryStatus == null) {
            deliveryStatus = "SENT";
        }
    }

    // ===== CONSTRUCTORS =====
    public BroadcastLog() {
        this.deliveryStatus = "SENT"; // default
    }

    // ===== GETTERS & SETTERS =====
    public Long getId() {
        return id;
    }

    public EventUpdate getEventUpdate() {
        return eventUpdate;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventUpdate(EventUpdate eventUpdate) {
        this.eventUpdate = eventUpdate;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
