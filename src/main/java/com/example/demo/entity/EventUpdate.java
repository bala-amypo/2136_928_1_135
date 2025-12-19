package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class EventUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generate ID
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id") // Set foreign key relation with Event
    private Event event;

    private String updateMessage;

    // Default constructor
    public EventUpdate() {}

    // Constructor with parameters
    public EventUpdate(Integer eventId, String updateMessage) {
        this.eventId = eventId;
        this.updateMessage = updateMessage;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventId() {
        return event != null ? event.getId() : null; // Get eventId from associated Event entity
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }
}
