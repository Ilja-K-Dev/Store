package com.project.inventory_service.application.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.project.inventory_service.application.command.replenish.ReplenishStockCommand;
import com.project.inventory_service.application.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReplenishStockCommandHandler {

    private final InventoryService service;

    @Transactional
    public void handle(ReplenishStockCommand cmd) {
        service.replenishStock(cmd.replenishedItems());
    }
}
