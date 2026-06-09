package com.project.product_service.domain.event;

import java.time.Instant;
import java.util.UUID;

public record ProductCreatedEvent(
                UUID eventId,
                UUID productId,
                String displayName,
                Instant occurredAt) {

        public static ProductCreatedEvent from(UUID productId, String displayName) {
                return new ProductCreatedEvent(UUID.randomUUID(), productId, displayName, Instant.now());
        }
}