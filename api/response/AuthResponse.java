package com.cit.backend.api.response;

import com.cit.backend.api.validator.JWTToken;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    @Valid
    @JWTToken
    String token;
}
