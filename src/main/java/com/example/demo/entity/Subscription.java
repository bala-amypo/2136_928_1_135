package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subscriptions") 
public class Subscription {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generate ID
    private Integer id;
    
    private Integer userId;
    private Integer eventId;

    // Default constructor
    public Subscription() {}

    // Constructor with parameters
    public Subscription(Integer userId, Integer eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}
