package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private String location;

    private String category;

    // Many events -> one publisher (User)
    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private User publisher;

    private Boolean isActive = true;

    private Timestamp createdAt;

    // Auto-generate createdAt and default isActive
    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        if (isActive == null) {
            isActive = true;
        }
    }

    // One event -> many updates
    @OneToMany(mappedBy = "event")
    private List<EventUpdate> updates;

    // One event -> many subscriptions
    @OneToMany(mappedBy = "event")
    private List<Subscription> subscriptions;

    // Constructors
    public Event() {}

    public Event(String title, String description, String location, String category, User publisher) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.category = category;
        this.publisher = publisher;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getLocation() {
        return location;
    }
 
    public void setLocation(String location) {
        this.location = location;
    }
 
    public String getCategory() {
        return category;
    }
 
    public void setCategory(String category) {
        this.category = category;
    }
 
    public User getPublisher() {
        return publisher;
    }
 
    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }
 
    public Boolean getIsActive() {
        return isActive;
    }
 
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
 
    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
