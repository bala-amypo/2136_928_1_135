package com.example.demo.dto;

import java.time.LocalDateTime;

public class BroadcastLogDTO {

    private Long id;
    private Long eventUpdateId;
    private Long subscriberId;
    private String deliveryStatus;
    private LocalDateTime sentAt;

    public BroadcastLogDTO() {}

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventUpdateId() {
        return eventUpdateId;
    }

    public void setEventUpdateId(Long eventUpdateId) {
        this.eventUpdateId = eventUpdateId;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
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
