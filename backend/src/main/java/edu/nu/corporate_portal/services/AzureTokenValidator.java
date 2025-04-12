package edu.nu.corporate_portal.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

@Service
public class AzureTokenValidator {

    private final JwtDecoder azureJwtDecoder;
    private final String expectedIssuer;
    private final String expectedAudience;

    public AzureTokenValidator(@Value("${azure.jwk-set-uri}") String jwkSetUri,
                               @Value("${azure.expected-issuer}") String expectedIssuer,
                               @Value("${azure.expected-audience}") String expectedAudience) {
        this.azureJwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
        this.expectedIssuer = expectedIssuer;
        this.expectedAudience = expectedAudience;
    }

    public Jwt validateToken(String token) throws JwtException {
        Jwt jwt = azureJwtDecoder.decode(token);

        if (!jwt.getIssuer().toString().equals(expectedIssuer)) {
            throw new JwtException("Invalid issuer: " + jwt.getIssuer());
        }

        if (!jwt.getAudience().contains(expectedAudience)) {
            throw new JwtException("Invalid audience: " + jwt.getAudience());
        }

        return jwt;
    }
}
