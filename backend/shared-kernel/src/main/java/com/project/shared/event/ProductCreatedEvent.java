package com.project.shared.event;

import java.time.Instant;
import java.util.UUID;

public record ProductCreatedEvent(
                UUID eventId,
                UUID productId,
                Instant occurredAt) {
                        
        public static ProductCreatedEvent from(UUID productId) {
                return new ProductCreatedEvent(UUID.randomUUID(), productId, Instant.now());
        }
}