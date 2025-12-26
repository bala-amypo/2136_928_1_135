package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus deliveryStatus;

    @Column(nullable = false)
    private Instant sentAt;

    @PrePersist
    protected void onCreate() {
        if (sentAt == null) {
            sentAt = Instant.now();
        }
        if (deliveryStatus == null) {
            deliveryStatus = DeliveryStatus.SENT;
        }
    }

    public BroadcastLog() {
        this.deliveryStatus = DeliveryStatus.SENT;
        this.sentAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public EventUpdate getEventUpdate() {
        return eventUpdate;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public Instant getSentAt() {
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

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public void setSentAt(Instant sentAt) {
        this.sentAt = sentAt;
    }
}
