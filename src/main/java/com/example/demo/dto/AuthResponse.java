// package com.example.demo.dto;

// public class AuthResponse {

//     private String token;
//     private Long userId;
//     private String email;
//     private String role;  // role as String

//     public AuthResponse(String token, Long userId, String email, String role) {
//         this.token = token;
//         this.userId = userId;
//         this.email = email;
//         this.role = role;
//     }

//     // ===== Getters & Setters =====

//     public String getToken() {
//         return token;
//     }

//     public void setToken(String token) {
//         this.token = token;
//     }

//     public Long getUserId() {
//         return userId;
//     }

//     public void setUserId(Long userId) {
//         this.userId = userId;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getRole() {
//         return role;
//     }

//     public void setRole(String role) {
//         this.role = role;
//     }
// }
package com.example.demo.dto;

public class AuthRequest {
    private String email; // required 
    private String password; // required 

    // Default Constructor
    public AuthRequest() {}

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}