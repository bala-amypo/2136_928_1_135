package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "event_updates")
public class EventUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(nullable = false, length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    private SeverityLevel severityLevel;

    // ✅ Tests EXPECT Instant (NOT LocalDateTime)
    @Column(nullable = false)
    private Instant timestamp;

    // ===== Lifecycle =====
    @PrePersist
    protected void onCreate() {
        this.timestamp = Instant.now();
    }

    // ===== Constructors =====
    public EventUpdate() {
    }

    public EventUpdate(Event event, String message, SeverityLevel severityLevel) {
        this.event = event;
        this.message = message;
        this.severityLevel = severityLevel;
        this.timestamp = Instant.now();
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    // ✅ Required by tests
    public void setId(Long id) {
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

    // ✅ Tests call this
    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    // ===== Backward compatibility (older controller/service code) =====
    public void setUpdateContent(String message) {
        this.message = message;
    }

    public void setUpdateType(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }
}
