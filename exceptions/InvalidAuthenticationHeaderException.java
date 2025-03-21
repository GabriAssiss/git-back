package com.cit.backend.exceptions;

public class InvalidAuthenticationHeaderException extends RuntimeException {
    public InvalidAuthenticationHeaderException() {
        super("Invalid Authentication Header");
    }

    public InvalidAuthenticationHeaderException(String message) {
        super(message);
    }

    public InvalidAuthenticationHeaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAuthenticationHeaderException(Throwable cause) {
        super(cause);
    }
}
