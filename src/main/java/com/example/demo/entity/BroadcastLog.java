package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "broadcasts") 
public class BroadcastLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID
    private Integer id;

    private Integer eventId;
    private String message;

    // Default constructor
    public BroadcastLog() {}

    // Constructor with parameters
    public BroadcastLog(Integer eventId, String message) {
        this.eventId = eventId;
        this.message = message;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
