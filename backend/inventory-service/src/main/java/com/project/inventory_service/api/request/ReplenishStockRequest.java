package com.project.inventory_service.api.request;

import java.util.List;

import com.project.inventory_service.application.command.replenish.ReplenishedStockItem;

public record ReplenishStockRequest(List<ReplenishedStockItem> replenishedItems) {
}