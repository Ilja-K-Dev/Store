package com.project.product_service.infrastructure.exception;

public class OutboxSerializationException extends RuntimeException {
    
    public OutboxSerializationException(String message) {
        super("Failed to serialize outbox event " + message);
    }
}
