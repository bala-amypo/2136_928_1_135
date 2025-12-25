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

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false, length = 1000)
    private String updateContent;

    @Column(nullable = false)
    private String updateType; // INFO / WARNING / CRITICAL

    private LocalDateTime postedAt;

    @PrePersist
    protected void onCreate() {
        postedAt = LocalDateTime.now();
    }

    // ===== RELATIONSHIPS =====

    @OneToMany(mappedBy = "eventUpdate")
    private List<BroadcastLog> broadcastLogs;

    // ===== CONSTRUCTORS =====

    public EventUpdate() {}

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getPostedAt() {
        return postedAt;
    }
}
