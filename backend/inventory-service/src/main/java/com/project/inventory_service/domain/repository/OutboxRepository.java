package com.project.inventory_service.domain.repository;

import java.util.List;
import java.util.UUID;

import com.project.inventory_service.domain.event.StockUnitCreatedEvent;
import com.project.inventory_service.infrastructure.persistence.entity.OutboxEventEntity;

public interface OutboxRepository {
    void save(StockUnitCreatedEvent event);

    void markAsProcessed(UUID id);

    List<OutboxEventEntity> findUnpublished();
}
