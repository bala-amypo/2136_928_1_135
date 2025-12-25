package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private String location;

    private String category;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private User publisher;

    @Column(nullable = false)
    private Boolean isActive = true;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (isActive == null) {
            isActive = true;
        }
    }

    // ===== RELATIONSHIPS =====

    @OneToMany(mappedBy = "event")
    private List<EventUpdate> updates;

    @OneToMany(mappedBy = "event")
    private List<Subscription> subscriptions;

    // ===== CONSTRUCTORS =====

    public Event() {}

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
