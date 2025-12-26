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
//     }
// }

package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Paste the long string here
    private String secret = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}

