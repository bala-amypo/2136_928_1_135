// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.Instant;

// @Entity
// @Table(name = "event_updates")
// public class EventUpdate {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "event_id", nullable = false)
//     private Event event;

//     @Column(nullable = false, length = 1000)
//     private String message;

//     @Enumerated(EnumType.STRING)
//     private SeverityLevel severityLevel;

//     // ✅ Tests expect Instant
//     @Column(nullable = false)
//     private Instant timestamp;

//     @PrePersist
//     public void onCreate() {
//         this.timestamp = Instant.now();
//     }

//     // ===== CONSTRUCTORS =====
//     public EventUpdate() {}

//     public EventUpdate(Event event, String message, SeverityLevel severityLevel) {
//         this.event = event;
//         this.message = message;
//         this.severityLevel = severityLevel;
//         this.timestamp = Instant.now();
//     }

//     // ===== GETTERS & SETTERS =====
//     public Long getId() {
//         return id;
//     }

//     // Required by tests
//     public void setId(Long id) {
//         this.id = id;
//     }

//     public Event getEvent() {
//         return event;
//     }

//     public void setEvent(Event event) {
//         this.event = event;
//     }

//     public String getMessage() {
//         return message;
//     }

//     public void setMessage(String message) {
//         this.message = message;
//     }

//     public SeverityLevel getSeverityLevel() {
//         return severityLevel;
//     }

//     public void setSeverityLevel(SeverityLevel severityLevel) {
//         this.severityLevel = severityLevel;
//     }

//     // ✅ Tests call this
//     public Instant getTimestamp() {
//         return timestamp;
//     }

//     public void setTimestamp(Instant timestamp) {
//         this.timestamp = timestamp;
//     }

//     // ===== Backward compatibility (old DB / tests) =====
//     public void setUpdateContent(String message) {
//         this.message = message;
//     }

//     public void setUpdateType(SeverityLevel severityLevel) {
//         this.severityLevel = severityLevel;
//     }
// }
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
    private Event event;

    private LocalDateTime timestamp;
    
    @Enumerated(EnumType.STRING)
    private SeverityLevel severityLevel;

    @PrePersist
    public void onCreate() {
        this.timestamp = LocalDateTime.now();
        if (this.severityLevel == null) {
            this.severityLevel = SeverityLevel.LOW; // Source 69
        }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public SeverityLevel getSeverityLevel() { return severityLevel; }
    public void setSeverityLevel(SeverityLevel severityLevel) { this.severityLevel = severityLevel; }
}