package com.project.product_service.domain.repository;

import java.util.List;
import java.util.UUID;

import com.project.product_service.domain.event.ProductCreatedEvent;
import com.project.product_service.infrastructure.persistence.entity.OutboxEventEntity;

public interface OutboxRepository {

    void save(ProductCreatedEvent event);

    void markAsProcessed(UUID id);

    List<OutboxEventEntity> findUnpublished(Integer limit);
}
