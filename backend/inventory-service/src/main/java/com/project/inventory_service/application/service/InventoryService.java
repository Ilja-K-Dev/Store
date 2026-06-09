package com.project.inventory_service.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.project.inventory_service.application.command.replenish.ReplenishedStockItem;
import com.project.inventory_service.domain.aggregate.StockUnit;
import com.project.inventory_service.domain.event.StockUnitCreatedEvent;
import com.project.inventory_service.domain.repository.OutboxRepository;
import com.project.inventory_service.domain.repository.StockPositionRepository;
import com.project.inventory_service.domain.repository.StockUnitRepository;
import com.project.inventory_service.infrastructure.persistence.repository.StockPositionJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final StockUnitRepository stockUnitRepository;
    private final StockPositionRepository stockPositionRepository;
    private final StockPositionJpaRepository stockPositionJpaRepository;
    private final OutboxRepository outboxRepository;

    public void createStockUnit(UUID productId, String displayName) {
        /* StockPosition stockPosition = stockPositionRepository.findEmpty()
                .orElseThrow(() -> new NoSpaceAvailableException(productId.toString())); */

        /*
         * StockPositionEntity stockPosition = stockPositionJpaRepository.
         * findFirstByStockUnitIsNullOrderByAisleAscRackAscBinAsc()
         * .orElseThrow(() -> new NoSpaceAvailableException(productId.toString()));
         */

        StockUnit stockUnit = StockUnit.create(productId);
        // stockUnitRepository.save(stockUnit, stockPosition);

        StockUnitCreatedEvent event = StockUnitCreatedEvent.from(
                stockUnit.getProductId(),
                displayName,
                0,
                0,
                0,
                "A",
                "A",
                "A");

        outboxRepository.save(event);
    }

    public void replenishStock(List<ReplenishedStockItem> replenishedStockItems) {
        for (ReplenishedStockItem item : replenishedStockItems) {
            stockUnitRepository.replenishStockItems(item.productId(), item.replenishedStock());
        }
    }

    public void receiveInboundStock() {
    }
}
