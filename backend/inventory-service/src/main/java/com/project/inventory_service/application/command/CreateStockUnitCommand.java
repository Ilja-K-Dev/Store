package com.project.inventory_service.application.command;

import java.util.UUID;

public record CreateStockUnitCommand(UUID productId, String displayName) {
}