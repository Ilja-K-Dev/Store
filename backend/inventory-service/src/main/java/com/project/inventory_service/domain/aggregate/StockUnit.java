package com.project.inventory_service.domain.aggregate;

import java.util.UUID;

import com.project.inventory_service.domain.exception.InvalidStockUnitDataException;
import com.project.inventory_service.domain.exception.OutOfStockException;
import com.project.inventory_service.domain.valueobject.StockPosition;

import lombok.Getter;

@Getter
public class StockUnit {

    private UUID id;
    private UUID productId;
    private Integer totalStock;
    private Integer reservedStock;
    private Integer inboundStock;
    private StockPosition stockPosition;

    private StockUnit() {
    }

    private StockUnit(
            UUID id,
            UUID productId,
            Integer totalStock,
            Integer reservedStock,
            Integer inboundStock) {

        this.id = id;
        this.productId = productId;
        this.totalStock = totalStock;
        this.reservedStock = reservedStock;
        this.inboundStock = inboundStock;
    }

    private StockUnit(
            UUID id,
            UUID productId,
            Integer totalStock,
            Integer reservedStock,
            Integer inboundStock,
            StockPosition stockPosition) {

        this.id = id;
        this.productId = productId;
        this.totalStock = totalStock;
        this.reservedStock = reservedStock;
        this.inboundStock = inboundStock;
    }

    public static StockUnit create(UUID productId) {
        try {
            return new StockUnit(UUID.randomUUID(), productId, 0, 0, 0);
        } catch (IllegalArgumentException ex) {
            throw new InvalidStockUnitDataException(ex.getMessage());
        }
    }

    public static StockUnit restore(
            UUID id,
            UUID productId,
            Integer totalStock,
            Integer reservedStock,
            Integer inboundStock,
            StockPosition stockPosition) {
        try {
            return new StockUnit(id, productId, totalStock, reservedStock, inboundStock, stockPosition);
        } catch (IllegalArgumentException ex) {
            throw new InvalidStockUnitDataException(ex.getMessage());
        }
    }

    public void reserveStockUnit(Integer reservedCount) {
        int availableStock = availableStock();
        if (availableStock > reservedCount) {
            this.reservedStock += reservedCount;
        } else if (availableStock < reservedCount && availableStock > 0) {
            this.reservedStock += availableStock;
        } else {
            throw new OutOfStockException("Out of stock for: " + this.productId);
        }
    }

    public void increaseInboundStock(Integer count) {
        this.inboundStock += count;
    }

    public void updateStockPosition(StockPosition stockPosition) {
        this.stockPosition = stockPosition;
    }

    private int availableStock() {
        return totalStock - reservedStock;
    }
}
