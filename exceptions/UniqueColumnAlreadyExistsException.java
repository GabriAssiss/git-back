package com.cit.backend.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UniqueColumnAlreadyExistsException extends RuntimeException {
    public UniqueColumnAlreadyExistsException() {
        super("Unique Column Already Exists");
    }

    public UniqueColumnAlreadyExistsException(String message) {
        super(message);
    }

    public UniqueColumnAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniqueColumnAlreadyExistsException(Throwable cause) {
        super("Unique Column Already Exists", cause);
    }
}
