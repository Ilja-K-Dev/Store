package com.project.inventory_service.infrastructure.persistence.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.project.inventory_service.domain.aggregate.StockUnit;
import com.project.inventory_service.domain.repository.StockUnitRepository;
import com.project.inventory_service.infrastructure.persistence.entity.StockPositionEntity;
import com.project.inventory_service.infrastructure.persistence.mapper.StockUnitMapper;
import com.project.inventory_service.infrastructure.persistence.repository.StockUnitJpaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class StockUnitRepositoryAdapter implements StockUnitRepository {

    private final StockUnitJpaRepository jpaRepository;
    private final StockUnitMapper mapper;

    @Override
    public void save(StockUnit product, StockPositionEntity stockPositionEntity) {
        //StockUnitEntity entity = mapper.toEntity(product, stockPositionEntity);

        //jpaRepository.save(entity);
    }

    @Override
    public Optional<StockUnit> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toAggregate);
    }

    @Override
    public void replenishStockItems(UUID productId, int amount) {
        jpaRepository.replenishStockItems(productId, amount);
    }
}