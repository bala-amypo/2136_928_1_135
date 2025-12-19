package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "e") 
public class EventUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID
    private Integer id;

    // Assuming eventId should be a reference to the Event entity
    @ManyToOne
    @JoinColumn(name = "eventId", referencedColumnName = "id", nullable = false) // Foreign key to Event
    private Event event; // Event entity reference

    private String updateMessage;

    // Default constructor
    public EventUpdate() {}

    // Constructor with parameters
    public EventUpdate(Event event, String updateMessage) {
        this.event = event;
        this.updateMessage = updateMessage;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }
}
    