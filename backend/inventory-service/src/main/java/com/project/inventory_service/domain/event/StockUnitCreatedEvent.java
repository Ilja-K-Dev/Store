package com.project.inventory_service.domain.event;

import java.time.Instant;
import java.util.UUID;

public record StockUnitCreatedEvent(
                UUID eventId,
                UUID productId,
                String displayName,
                Integer totalStock,
                Integer reservedStock,
                Integer inboundStock,
                String aisle,
                String rack,
                String bin,
                Instant occurredAt) {

        public static StockUnitCreatedEvent from(
                        UUID productId,
                        String displayName,
                        Integer totalStock,
                        Integer reservedStock,
                        Integer inboundStock,
                        String aisle,
                        String rack,
                        String bin) {
                return new StockUnitCreatedEvent(
                                UUID.randomUUID(),
                                productId,
                                displayName,
                                totalStock,
                                reservedStock,
                                inboundStock,
                                aisle,
                                rack,
                                bin,
                                Instant.now());
        }
}