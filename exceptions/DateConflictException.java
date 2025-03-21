package com.cit.backend.exceptions;

public class DateConflictException extends RuntimeException {
    public DateConflictException(String message) {
        super(message);
    }

    public DateConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateConflictException(Throwable cause) {
        super(cause);
    }
}
