package com.servlet.project.exceptions;

public class TopicAlreadyAssigned extends RuntimeException {
    public TopicAlreadyAssigned() {
        super();
    }

    public TopicAlreadyAssigned(String message) {
        super(message);
    }

    public TopicAlreadyAssigned(String message, Throwable cause) {
        super(message, cause);
    }
}
