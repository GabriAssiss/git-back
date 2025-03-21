package com.cit.backend.exceptions;

public class EmployeeNotRegisteredInACondominiumException extends RuntimeException {
    public EmployeeNotRegisteredInACondominiumException(String message) {
        super(message);
    }

    public EmployeeNotRegisteredInACondominiumException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeNotRegisteredInACondominiumException(Throwable cause) {
        super(cause);
    }
}
