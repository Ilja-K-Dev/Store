package com.project.inventory_service.application.command.replenish;

import java.util.UUID;

public record ReplenishedStockItem(UUID productId, Integer replenishedStock) {
}
