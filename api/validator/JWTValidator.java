package com.cit.backend.api.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class JWTValidator implements ConstraintValidator<JWTToken, String> {
    @Override
    public void initialize(JWTToken constraintAnnotation) {
    }

    @Override
    public boolean isValid(String jwtField, ConstraintValidatorContext context) {
        if (jwtField == null) {
            return false;
        }
        // Regex pattern for JWT validation
        return jwtField.matches("^(?:[A-Za-z0-9-_]+(?:\\.|$)){3}");
    }
}
