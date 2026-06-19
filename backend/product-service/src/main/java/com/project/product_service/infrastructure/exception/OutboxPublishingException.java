package com.project.product_service.infrastructure.exception;

public class OutboxPublishingException extends RuntimeException {
    
    public OutboxPublishingException(String message) {
        super("Failed to deserialize outbox event " + message);
    }
}
