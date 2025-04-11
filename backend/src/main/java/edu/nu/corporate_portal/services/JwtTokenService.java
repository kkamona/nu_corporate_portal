package edu.nu.corporate_portal.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtTokenService {

    private final Algorithm algorithm = Algorithm.HMAC256("your-secret-key");

    public String generateAccessToken(String userId) {
        return JWT.create()
                .withSubject(userId)
                .withExpiresAt(Date.from(Instant.now().plus(Duration.ofHours(2))))
                .sign(algorithm);
    }

    public String generateRefreshToken(String userId) {
        return JWT.create()
                .withSubject(userId)
                .withExpiresAt(Date.from(Instant.now().plus(Duration.ofDays(14))))
                .sign(algorithm);
    }
}
