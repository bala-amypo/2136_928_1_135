package com.example.demo.entity;

public class Subscription {

    private Integer id;
    private Integer userId;
    private Integer eventId;

    public Subscription() {}

    public Subscription(Integer id, Integer userId, Integer eventId) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}
