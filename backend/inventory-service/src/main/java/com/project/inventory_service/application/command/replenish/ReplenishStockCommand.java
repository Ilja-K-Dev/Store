package com.project.inventory_service.application.command.replenish;

import java.util.List;

public record ReplenishStockCommand(List<ReplenishedStockItem> replenishedItems) {
}