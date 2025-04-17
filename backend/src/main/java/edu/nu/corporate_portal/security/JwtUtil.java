package edu.nu.corporate_portal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email) {
        try {
            System.out.println("JWT UTIL: Generating token for email: " + email);
            if (secret == null || secret.isEmpty()) {
                throw new IllegalStateException("JWT secret is not configured!");
            }
            System.out.println("JWT UTIL: Secret value: " + secret);
            System.out.println("JWT UTIL: Secret length: " + secret.length() + " characters");
            byte[] secretBytes = secret.getBytes();
            if (secretBytes.length < 32) {
                throw new IllegalArgumentException("JWT secret is too short! It must be at least 32 bytes long.");
            }
            var key = Keys.hmacShaKeyFor(secretBytes);
            String token = Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
            System.out.println("JWT UTIL: Token generated successfully: " + token);
            return token;
        } catch (Exception ex) {
            System.out.println("JWT UTIL: Error generating token for email: " + email);
            ex.printStackTrace();
            throw ex;
        }
    }

    public Claims parseClaims(String token) {
        byte[] keyBytes = secret.getBytes();
        var key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
