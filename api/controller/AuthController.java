package com.cit.backend.api.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cit.backend.api.request.AuthRequest;
import com.cit.backend.api.response.AuthResponse;
import com.cit.backend.api.response.TokenVerificationResponse;
import com.cit.backend.domain.entity.Profile;
import com.cit.backend.domain.service.AuthTokenService;
import com.cit.backend.exceptions.InvalidTokenException;
import com.cit.backend.exceptions.UserDoesNotExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        final String email = request.getEmail();
        final String password = request.getPassword();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        Profile userDetails = (Profile) authentication.getPrincipal();

        AuthResponse response = new AuthResponse(authTokenService.generateToken(userDetails));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<TokenVerificationResponse> verifyToken(@PathVariable("token") String token) {
        try {
            if(!authTokenService.doesUserExists(token))
                throw new UserDoesNotExistsException("The user for this token does not exists");

            boolean isTokenExpired = authTokenService.isTokenExpired(token);
            Set<String> tokenPermissions = authTokenService.getRoles(token);
            return ResponseEntity.ok(new TokenVerificationResponse(isTokenExpired, tokenPermissions));
        } catch (JWTVerificationException e) {
            throw new InvalidTokenException("Invalid token", e);
        }
    }
}
