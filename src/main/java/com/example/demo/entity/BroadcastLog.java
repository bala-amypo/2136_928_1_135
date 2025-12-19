package com.example.demo.entity;

public class BroadcastLog {

    private Integer id;
    private Integer eventId;
    private String message;

    public BroadcastLog() {}

    public BroadcastLog(Integer id, Integer eventId, String message) {
        this.id = id;
        this.eventId = eventId;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
