
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private String category;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private User publisher;

    private boolean isActive = true;
    private Instant createdAt;
    private Instant lastUpdatedAt;

    public Event() {}

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
        this.lastUpdatedAt = Instant.now();
        this.isActive = true; 
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdatedAt = Instant.now(); 
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public User getPublisher() { return publisher; }
    public void setPublisher(User publisher) { this.publisher = publisher; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getLastUpdatedAt() { return lastUpdatedAt; }
}