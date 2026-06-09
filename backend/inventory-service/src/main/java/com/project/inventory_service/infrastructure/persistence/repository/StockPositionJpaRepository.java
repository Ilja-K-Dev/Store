package com.project.inventory_service.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.inventory_service.infrastructure.persistence.entity.StockPositionEntity;

public interface StockPositionJpaRepository extends JpaRepository<StockPositionEntity, UUID> {
    Optional<StockPositionEntity> findFirstByStockUnitIsNullOrderByAisleAscRackAscBinAsc();
}
