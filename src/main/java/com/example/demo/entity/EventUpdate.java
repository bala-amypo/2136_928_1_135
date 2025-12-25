package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_updates")
public class EventUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false, length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    private SeverityLevel severityLevel;

    private LocalDateTime timestamp;

    @PrePersist
    public void onCreate() {
        this.timestamp = LocalDateTime.now();
    }

    // ===== CONSTRUCTORS =====
    public EventUpdate() {}

    public EventUpdate(Event event, String message, SeverityLevel severityLevel) {
        this.event = event;
        this.message = message;
        this.severityLevel = severityLevel;
        this.timestamp = LocalDateTime.now();
    }

    // ===== GETTERS & SETTERS =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) { // added for tests
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SeverityLevel getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Optional helper methods for backward compatibility
    public void setUpdateContent(String message) {
        this.message = message;
    }

    public void setUpdateType(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }
}
