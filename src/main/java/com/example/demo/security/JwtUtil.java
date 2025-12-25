package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    // Generate token
    public String generateToken(Long id, String email, String role) {
        return Jwts.builder()
                .claim("id", id)
                .claim("role", role)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Validate token
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Get email (username) from token
    public String getUsernameFromToken(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String getEmailFromToken(String token) {
        return getUsernameFromToken(token);
    }

    // ===== Methods required by test =====

    // Extract user ID from token
    public Long getUserIdFromToken(String token) {
        return extractAllClaims(token).get("id", Long.class);
    }

    // Extract role from token
    public String getRoleFromToken(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // ===== Helper =====
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
