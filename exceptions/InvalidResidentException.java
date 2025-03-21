package com.cit.backend.exceptions;

public class InvalidResidentException extends RuntimeException {
    public InvalidResidentException(String message) {
        super(message);
    }

    public InvalidResidentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidResidentException(Throwable cause) {
        super(cause);
    }
}
