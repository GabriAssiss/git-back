package com.cit.backend.domain.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cit.backend.api.validator.JWTToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Map;

public abstract class JWTService {
    @Value("${api.security.jwt.secret}")
    private String SECRET;

    @Value("${api.security.jwt.expiration}")
    private Long EXPIRATION;

    private static final String ISSUER = "CIT-APPLICATION-API";

    private Algorithm getSignKey() {
        return Algorithm.HMAC256(SECRET);
    }

    protected String buildToken(String subject) {
        return buildToken(subject, true, null);
    }

    protected String buildToken(String subject, boolean expires) {
        return buildToken(subject, expires, null);
    }

    protected String buildToken(String subject, Map<String, ?> payload) {
        return buildToken(subject, true, payload);
    }

    protected String buildToken(
            String subject,
            boolean expires,
            Map<String, ?> payload
    ) {
        JWTCreator.Builder builder = JWT
                .create()
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withSubject(subject);
        if (expires) builder.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION));
        if (payload != null) builder.withPayload(payload);
        return builder.sign(getSignKey());
    }

    protected Boolean isTokenValid(@Valid @JWTToken String token, String subject) {
        return !isTokenExpired(token) && JWT
                .require(getSignKey())
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject()
                .equals(subject);
    }

    private DecodedJWT decode(String token) {
        return JWT.decode(token);
    }

    public Boolean isTokenExpired(@Valid @JWTToken String token) {
        return decode(token).getExpiresAt().before(new Date());
    }

    public String getSubject(@Valid @JWTToken String token) {
        return decode(token).getSubject();
    }
}
