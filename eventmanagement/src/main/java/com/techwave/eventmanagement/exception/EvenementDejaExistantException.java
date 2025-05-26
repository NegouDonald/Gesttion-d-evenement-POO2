package com.techwave.eventmanagement.exception;

public class EvenementDejaExistantException extends RuntimeException {
    public EvenementDejaExistantException(String message) {
        super(message);
    }
}
