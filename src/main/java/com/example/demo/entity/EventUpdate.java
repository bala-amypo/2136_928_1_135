package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "event_updates")
public class EventUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many updates -> one event
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(length = 1000)
    private String updateContent;

    // INFO / WARNING / CRITICAL
    private String updateType;

    private Timestamp postedAt;

    // Auto-generate postedAt
    @PrePersist
    protected void onCreate() {
        this.postedAt = new Timestamp(System.currentTimeMillis());
    }

    // One update -> many broadcast logs
    @OneToMany(mappedBy = "eventUpdate")
    private List<BroadcastLog> broadcastLogs;

    // Constructors
    public EventUpdate() {}

    public EventUpdate(Event event, String updateContent, String updateType) {
        this.event = event;
        this.updateContent = updateContent;
        this.updateType = updateType;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public Timestamp getPostedAt() {
        return postedAt;
    }
}
