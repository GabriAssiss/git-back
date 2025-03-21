package com.cit.backend.exceptions;

public class InvalidRangeOfTimeException extends RuntimeException {
    public InvalidRangeOfTimeException(String message) {
        super(message);
    }

    public InvalidRangeOfTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRangeOfTimeException(Throwable cause) {
        super(cause);
    }
}
