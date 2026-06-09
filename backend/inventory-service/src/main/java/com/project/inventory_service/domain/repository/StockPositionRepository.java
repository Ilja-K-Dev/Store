package com.project.inventory_service.domain.repository;

import java.util.Optional;

import com.project.inventory_service.domain.valueobject.StockPosition;

public interface StockPositionRepository {
    Optional<StockPosition> findEmpty();
}
