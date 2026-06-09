package com.project.inventory_service.domain.valueobject;

import java.util.UUID;

public record StockPosition(UUID id, String aisle, String rack, String bin) {
    public StockPosition {
        if (aisle == null || aisle.isBlank()) {
            throw new IllegalArgumentException("Aisle is empty");
        }
        if (rack == null || rack.isBlank()) {
            throw new IllegalArgumentException("Row is empty");
        }
        if (bin == null || bin.isBlank()) {
            throw new IllegalArgumentException("Bin is empty");
        }

    }
}
