
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
    @JoinColumn(name = "event_id")
    private Event event;

    private String updateContent;
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private SeverityLevel severityLevel;

    public EventUpdate() {}

    @PrePersist
    public void onCreate() {
        this.timestamp = LocalDateTime.now();
        if (this.severityLevel == null) {
            this.severityLevel = SeverityLevel.LOW; // Default severity 
        }
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    public String getUpdateContent() { return updateContent; }
    public void setUpdateContent(String updateContent) { this.updateContent = updateContent; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public SeverityLevel getSeverityLevel() { return severityLevel; }
    public void setSeverityLevel(SeverityLevel severityLevel) { this.severityLevel = severityLevel; }
}