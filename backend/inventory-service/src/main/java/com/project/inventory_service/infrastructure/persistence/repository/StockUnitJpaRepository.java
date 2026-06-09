package com.project.inventory_service.infrastructure.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.project.inventory_service.infrastructure.persistence.entity.StockUnitEntity;

public interface StockUnitJpaRepository extends JpaRepository<StockUnitEntity, UUID> {

    @Modifying
    @Transactional
    @Query("UPDATE StockUnitEntity e SET e.inboundStock = :amount WHERE e.productId = :productId")
    void replenishStockItems(@Param("productId") UUID productId, @Param("amount") Integer amount);
}