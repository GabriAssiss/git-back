package com.cit.backend.api.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext context) {
        if (phoneField == null) {
            return false;
        }
        // Regex pattern for phone number validation
        return phoneField.matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}");
    }
}