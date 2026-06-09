package com.project.inventory_service.infrastructure.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.project.inventory_service.domain.repository.StockPositionRepository;
import com.project.inventory_service.domain.valueobject.StockPosition;
import com.project.inventory_service.infrastructure.persistence.mapper.StockPositionMapper;
import com.project.inventory_service.infrastructure.persistence.repository.StockPositionJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StockPositionRepositoryAdapter implements StockPositionRepository {
    
    private final StockPositionJpaRepository jpaRepository;
    private final StockPositionMapper mapper;

    @Override
    public Optional<StockPosition> findEmpty() {
        return jpaRepository.findFirstByStockUnitIsNullOrderByAisleAscRackAscBinAsc().map(mapper::toAggregate);
    }

}