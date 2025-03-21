package com.cit.backend.exceptions;

public class FileDoesNotExistsException extends RuntimeException {
    public FileDoesNotExistsException(String message) {
        super(message);
    }

    public FileDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileDoesNotExistsException(Throwable cause) {
        super(cause);
    }
}
