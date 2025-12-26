// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.Instant;
// import java.util.List;

// @Entity
// @Table(name = "events")
// public class Event {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false)
//     private String title;

//     @Column(nullable = false, length = 1000)
//     private String description;

//     @Column(nullable = false)
//     private String location;

//     private String category;

//     @ManyToOne
//     @JoinColumn(name = "publisher_id", nullable = false)
//     private User publisher;

//     @Column(nullable = false)
//     private Boolean isActive = true;

//     // ✅ FIX: Tests expect Instant
//     @Column(nullable = false)
//     private Instant createdAt;

//     @Column(nullable = false)
//     private Instant lastUpdatedAt;

//     @PrePersist
//     public void onCreate() {
//         Instant now = Instant.now();
//         createdAt = now;
//         lastUpdatedAt = now;
//         if (isActive == null) {
//             isActive = true;
//         }
//     }

//     @PreUpdate
//     public void onUpdate() {
//         lastUpdatedAt = Instant.now();
//     }

//     // ===== RELATIONSHIPS =====
//     @OneToMany(mappedBy = "event")
//     private List<EventUpdate> updates;

//     @OneToMany(mappedBy = "event")
//     private List<Subscription> subscriptions;

//     // ===== CONSTRUCTORS =====
//     public Event() {}

//     // ===== GETTERS & SETTERS =====
//     public Long getId() {
//         return id;
//     }

//     // Required by tests
//     public void setId(Long id) {
//         this.id = id;
//     }

//     public void setId(long id) {
//         this.id = id;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public void setTitle(String title) {
//         this.title = title;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public String getLocation() {
//         return location;
//     }

//     public void setLocation(String location) {
//         this.location = location;
//     }

//     public String getCategory() {
//         return category;
//     }

//     public void setCategory(String category) {
//         this.category = category;
//     }

//     public User getPublisher() {
//         return publisher;
//     }

//     public void setPublisher(User publisher) {
//         this.publisher = publisher;
//     }

//     // Primitive getter/setter for tests
//     public boolean isActive() {
//         return isActive != null && isActive;
//     }

//     public void setActive(boolean active) {
//         this.isActive = active;
//     }

//     // Boxed getter/setter for JPA
//     public Boolean getIsActive() {
//         return isActive;
//     }

//     public void setIsActive(Boolean active) {
//         this.isActive = active;
//     }

//     // ✅ Tests may call these
//     public Instant getCreatedAt() {
//         return createdAt;
//     }

//     public Instant getLastUpdatedAt() {
//         return lastUpdatedAt;
//     }

//     public void setLastUpdatedAt(Instant lastUpdatedAt) {
//         this.lastUpdatedAt = lastUpdatedAt;
//     }
// }
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
        this.isActive = true; // Default state [cite: 128]
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdatedAt = Instant.now(); // Updates on every change [cite: 128]
    }

    // Getters and Setters
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