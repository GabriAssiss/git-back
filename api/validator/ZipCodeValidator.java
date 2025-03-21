package com.cit.backend.api.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

    @Override
    public void initialize(ZipCode constraintAnnotation) {
    }

    @Override
    public boolean isValid(String zipCodeField, ConstraintValidatorContext context) {
        if (zipCodeField == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Zip code cannot be null")
                    .addConstraintViolation();
            return false;
        }
        // Regex pattern for zip code validation (Brazilian format)
        boolean isValid = zipCodeField.matches("\\d{5}-\\d{3}");
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid zip code format. Expected format is XXXXX-XXX.")
                    .addConstraintViolation();
        }
        return isValid;
    }
}