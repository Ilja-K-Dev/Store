package com.project.inventory_service.application.handler;

import org.springframework.stereotype.Component;

import com.project.inventory_service.application.command.CreateStockUnitCommand;

import lombok.RequiredArgsConstructor;
import com.project.inventory_service.application.service.InventoryService;

@Component
@RequiredArgsConstructor
public class CreateStockUnitCommandHandler {

    private final InventoryService service;

    public void handle(CreateStockUnitCommand cmd) {
        service.createStockUnit(cmd.productId(), cmd.displayName());
    }
}
