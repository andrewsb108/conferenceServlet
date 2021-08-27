package com.servlet.project.exceptions;

public class UniqueParticipantException extends RuntimeException {
    public UniqueParticipantException() {
        super();
    }

    public UniqueParticipantException(String message) {
        super(message);
    }

    public UniqueParticipantException(String message, Throwable cause) {
        super(message, cause);
    }
}
