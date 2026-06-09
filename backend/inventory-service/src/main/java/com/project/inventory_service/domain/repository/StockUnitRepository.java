package com.project.inventory_service.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.project.inventory_service.domain.aggregate.StockUnit;
import com.project.inventory_service.infrastructure.persistence.entity.StockPositionEntity;

public interface StockUnitRepository {

    void save(StockUnit product, StockPositionEntity stockPositionEntity);

    Optional<StockUnit> findById(UUID id);

    void replenishStockItems(UUID productId, int amount);
}
