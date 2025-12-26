// package com.example.demo.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import java.util.Date;

// @Component
// public class JwtUtil {

//     @Value("${jwt.secret}")
//     private String secretKey;

//     private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

//     // ✅ REQUIRED by Spring (DO NOT REMOVE)
//     public JwtUtil() {
//     }

//     // ================= TOKEN GENERATION =================
//     public String generateToken(Long id, String email, String role) {
//         return Jwts.builder()
//                 .setSubject(email)
//                 .claim("id", id)
//                 .claim("role", role)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                 .signWith(SignatureAlgorithm.HS256, secretKey)
//                 .compact();
//     }

//     // ================= VALIDATION =================
//     public boolean validateToken(String token) {
//         try {
//             extractAllClaims(token);
//             return true;
//         } catch (Exception ex) {
//             return false;
//         }
//     }

//     // ================= EXTRACTION =================
//     public String getEmailFromToken(String token) {
//         return extractAllClaims(token).getSubject();
//     }

//     public Long getUserIdFromToken(String token) {
//         return extractAllClaims(token).get("id", Long.class);
//     }

//     public String getRoleFromToken(String token) {
//         return extractAllClaims(token).get("role", String.class);
//     }

//     // ================= INTERNAL =================
//     private Claims extractAllClaims(String token) {
//         return Jwts.parser()
//                 .setSigningKey(secretKey)
//                 .parseClaimsJws(token)
//                 .getBody();
//     }}

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

    // 1. Added empty constructor (Required by Spring and some test setups)
    public JwtUtil() {}

    // 2. Added constructor used by Test Line 58
    public JwtUtil(String secret, int expiration) {
        this.secret = secret;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String getUsernameFromToken(String token) {
        return extractUsername(token);
    }

    // 3. Added method required by Test Line 657
    public Long getUserIdFromToken(String token) {
        return extractClaim(token, claims -> claims.get("id", Long.class));
    }

    // 4. Added method required by Test Line 658 & 691
    public String getRoleFromToken(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    // Method used by Tests
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Overloaded method used by AuthController
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

    // 5. Overloaded validateToken for single argument (Required by Test Lines 656, 665, 685, 699)
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