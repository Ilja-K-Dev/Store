package com.project.inventory_service.domain.exception;

public class NoSpaceAvailableException extends RuntimeException {
    public NoSpaceAvailableException(String message) {
        super("No space available for: " + message);
    }

}
