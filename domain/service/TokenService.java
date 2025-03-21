package com.cit.backend.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class   TokenService extends JWTService {
    public String generateToken() {
        String uniqueSubject = UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
        return buildToken(uniqueSubject, false);
    }
}
