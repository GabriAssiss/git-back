package com.cit.backend.api.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ZipCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZipCode {
    String message() default "Invalid zip code format. Expected format is XXXXX-XXX.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String regexp() default "\\d{5}-\\d{3}";
    Pattern.Flag[] flags() default {};
}