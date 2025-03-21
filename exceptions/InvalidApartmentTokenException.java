package com.cit.backend.exceptions;

public class InvalidApartmentTokenException extends RuntimeException {
    public InvalidApartmentTokenException(String message) {
        super(message);
    }

    public InvalidApartmentTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidApartmentTokenException(Throwable cause) {
        super(cause);
    }
}
