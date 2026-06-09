package com.project.inventory_service.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.project.inventory_service.domain.valueobject.StockPosition;
import com.project.inventory_service.infrastructure.persistence.entity.StockPositionEntity;

@Component
public class StockPositionMapper {

    public StockPosition toAggregate(StockPositionEntity entity) {
        return new StockPosition(
                entity.getId(),
                entity.getAisle(),
                entity.getRack(),
                entity.getBin());
    }
}