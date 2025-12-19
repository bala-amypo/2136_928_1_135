package com.example.demo.entity;
import jakarta.persistence.*;
@Entity
public class EventUpdate {
    @Id
    private Integer id;
    private Integer eventId;
    private String updateMessage;

    public EventUpdate() {}

    public EventUpdate(Integer id, Integer eventId, String updateMessage) {
        this.id = id;
        this.eventId = eventId;
        this.updateMessage = updateMessage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }
}
