package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        // Convert role string to Role enum if provided
        if (request.getRole() != null) {
            Role role = Role.valueOf(request.getRole().toUpperCase());
            user.setRole(role);
        }

        User saved = userService.registerUser(user);

        String roleStr = saved.getRole() != null ? saved.getRole().name() : null;
        String token = jwtUtil.generateToken(saved.getId(), saved.getEmail(), roleStr);

        return ResponseEntity.ok(
                new AuthResponse(token, saved.getId(), saved.getEmail(), roleStr)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        User user = userService.findByEmail(request.getEmail());
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        String roleStr = user.getRole() != null ? user.getRole().name() : null;
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), roleStr);

        return ResponseEntity.ok(
                new AuthResponse(token, user.getId(), user.getEmail(), roleStr)
        );
    }
}
