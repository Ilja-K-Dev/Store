package com.project.inventory_service.domain.repository;

import com.project.inventory_service.application.command.CreateStockUnitCommand;

public interface InboxRepository {

    void save(CreateStockUnitCommand cmd);
}
