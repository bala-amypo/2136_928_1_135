package com.example.demo.entity;
import jakarta.persistence.*;
public class Event {

    private Integer id;
    private String title;
    private String description;
    private String location;
    private String date;

    public Event() {}

    public Event(Integer id, String title, String description, String location, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
