package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "event_updates")
public class EventUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Event reference is required
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(length = 1000, nullable = false)
    private String updateContent;

    @Column(nullable = false)
    private String updateType; // INFO / WARNING / CRITICAL

    @Column(nullable = false)
    private String severityLevel = "LOW"; // default

    @Column(nullable = false, updatable = false)
    private LocalDateTime postedAt;

    // One-to-many relationship with BroadcastLog, removed invalid cascade
    @OneToMany(mappedBy = "eventUpdate", fetch = FetchType.LAZY)
    private List<BroadcastLog> broadcastLogs;

    // Automatically set timestamp
    @PrePersist
    protected void onCreate() {
        this.postedAt = LocalDateTime.now();
    }

    // Constructors
    public EventUpdate() {}

    public EventUpdate(Event event, String updateContent, String updateType) {
        this.event = event;
        this.updateContent = updateContent;
        this.updateType = updateType;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    public String getUpdateContent() { return updateContent; }
    public void setUpdateContent(String updateContent) { this.updateContent = updateContent; }
    public String getUpdateType() { return updateType; }
    public void setUpdateType(String updateType) { this.updateType = updateType; }
    public String getSeverityLevel() { return severityLevel; }
    public void setSeverityLevel(String severityLevel) { this.severityLevel = severityLevel; }
    public LocalDateTime getPostedAt() { return postedAt; }
}
