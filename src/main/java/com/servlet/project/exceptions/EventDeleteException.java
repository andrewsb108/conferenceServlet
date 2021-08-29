package com.servlet.project.exceptions;

public class EventDeleteException extends RuntimeException {
    public EventDeleteException() {
        super();
    }

    public EventDeleteException(String message) {
        super(message);
    }

    public EventDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
