

package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {
    private String secret = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    
    public JwtUtil() {}


    public JwtUtil(String secret, int expiration) {
        this.secret = secret;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String getUsernameFromToken(String token) {
        return extractUsername(token);
    }

    
    public Long getUserIdFromToken(String token) {
        return extractClaim(token, claims -> claims.get("id", Long.class));
    }

    
    public String getRoleFromToken(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    
    public String generateToken(Long id, String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("role", role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    
    public Boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}