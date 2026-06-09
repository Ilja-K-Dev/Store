package com.project.inventory_service.infrastructure.persistence.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.project.inventory_service.domain.aggregate.StockUnit;
import com.project.inventory_service.domain.valueobject.StockPosition;
import com.project.inventory_service.infrastructure.persistence.entity.StockPositionEntity;
import com.project.inventory_service.infrastructure.persistence.entity.StockUnitEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StockUnitMapper {

    public StockUnitEntity toEntity(StockUnit stockUnit, StockPositionEntity stockPositionEntity) {
        StockUnitEntity entity = new StockUnitEntity();

        entity.setProductId(stockUnit.getProductId());
        entity.setTotalStock(stockUnit.getTotalStock());
        entity.setReservedStock(stockUnit.getReservedStock());
        entity.setInboundStock(stockUnit.getInboundStock());
        entity.setStockPosition(stockPositionEntity);
        entity.setCreatedAt(Instant.now());

        return entity;
    }

    public StockUnit toAggregate(StockUnitEntity entity) {
        StockPosition position = new StockPosition(
                entity.getStockPosition().getId(),
                entity.getStockPosition().getAisle(),
                entity.getStockPosition().getRack(),
                entity.getStockPosition().getBin());

        return StockUnit.restore(
                entity.getId(),
                entity.getProductId(),
                entity.getTotalStock(),
                entity.getReservedStock(),
                entity.getInboundStock(),
                position);
    }
}