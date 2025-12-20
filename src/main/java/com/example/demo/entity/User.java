package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "user") 
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates ID
    private Integer id;

    private String username;
    private String password;
    private String role;
    private String email;

    // Default constructor
    public User() {}

    // Constructor with parameters
    public User(String username, String password, String role,String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email=email
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
