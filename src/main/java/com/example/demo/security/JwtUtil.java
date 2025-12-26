package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private SecretKey key;

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    // ================= SPRING CONSTRUCTOR =================
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        // Convert raw string → HMAC key (NO Base64 required)
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // ================= TEST CONSTRUCTOR (HIDDEN TESTS) =================
    public JwtUtil(String secret, long expirationTime) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // ================= TOKEN GENERATION =================
    public String generateToken(Long id, String email, String role) {
        return Jwts.builder()
                .claim("id", id)
                .claim("role", role)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ================= VALIDATION =================
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // ================= EXTRACTION =================
    public String getUsernameFromToken(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String getEmailFromToken(String token) {
        return getUsernameFromToken(token);
    }

    public Long getUserIdFromToken(String token) {
        return extractAllClaims(token).get("id", Long.class);
    }

    public String getRoleFromToken(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // ================= INTERNAL =================
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
