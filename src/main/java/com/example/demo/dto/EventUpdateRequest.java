// package com.example.demo.dto;

// public class EventUpdateRequest {
//     private Long eventId;
//     private String updateContent;
//     private String updateType;

//     public EventUpdateRequest() {}

//     public Long getEventId() {
//         return eventId;
//     }
    
//     public void setEventId(Long eventId) {
//         this.eventId = eventId;
//     }

//     public String getUpdateContent() {
//         return updateContent;
//     }
    
//     public void setUpdateContent(String updateContent) {
//         this.updateContent = updateContent;
//     }

//     public String getUpdateType() {
//         return updateType;
//     }
    
//     public void setUpdateType(String updateType) {
//         this.updateType = updateType;
//     }
// }


package com.example.demo.dto;

public class EventUpdateRequest {
    private Long eventId;         // required; target Event id [cite: 246, 280]
    private String updateContent; // required [cite: 246, 280]
    private String updateType;    // INFO, WARNING, or CRITICAL [cite: 246, 280]

    // Getters and Setters
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public String getUpdateContent() { return updateContent; }
    public void setUpdateContent(String updateContent) { this.updateContent = updateContent; }
    public String getUpdateType() { return updateType; }
    public void setUpdateType(String updateType) { this.updateType = updateType; }
}