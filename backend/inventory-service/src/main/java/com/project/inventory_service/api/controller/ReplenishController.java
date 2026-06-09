package com.project.inventory_service.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.inventory_service.api.request.ReplenishStockRequest;
import com.project.inventory_service.application.command.replenish.ReplenishStockCommand;
import com.project.inventory_service.application.handler.ReplenishStockCommandHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("stock-unit")
@RequiredArgsConstructor
public class ReplenishController {

    private final ReplenishStockCommandHandler handler;

    @PutMapping("/replenish")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void replenishStockItems(@Valid @RequestBody ReplenishStockRequest request) {
        ReplenishStockCommand command = new ReplenishStockCommand(request.replenishedItems());
        handler.handle(command);
    }
}