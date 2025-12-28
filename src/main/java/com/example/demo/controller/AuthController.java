


package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.entity.Role;
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
        
        user.setFullName(request.fullName);
        user.setEmail(request.email);
        user.setPassword(request.password);
        user.setRole(Role.valueOf(request.role));
        
        User saved = userService.register(user);
        String token = jwtUtil.generateToken(saved.getId(), saved.getEmail(), saved.getRole().name());
        return ResponseEntity.status(201).body(new AuthResponse(token, saved.getId(), saved.getEmail(), saved.getRole().name()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        
        User user = userService.findByEmail(request.getEmail());
        
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole().name());
        return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), user.getRole().name()));
    }
}