package com.servlet.project.exceptions;

public class TopicAlreadyExistException extends RuntimeException {

    public TopicAlreadyExistException() {
        super();
    }

    public TopicAlreadyExistException(String message) {
        super(message);
    }

    public TopicAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
