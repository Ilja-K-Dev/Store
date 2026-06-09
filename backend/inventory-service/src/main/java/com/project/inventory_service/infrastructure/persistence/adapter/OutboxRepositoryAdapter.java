package com.project.inventory_service.infrastructure.persistence.adapter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.inventory_service.domain.event.StockUnitCreatedEvent;
import com.project.inventory_service.domain.repository.OutboxRepository;
import com.project.inventory_service.domain.event.EventType;
import com.project.inventory_service.infrastructure.persistence.entity.OutboxEventEntity;
import com.project.inventory_service.infrastructure.persistence.repository.OutboxJpaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OutboxRepositoryAdapter implements OutboxRepository {
    
    private final OutboxJpaRepository jpaRepository;
    private final ObjectMapper mapper;

    @Override
    public void save(StockUnitCreatedEvent event) {
        OutboxEventEntity outboxEvent = new OutboxEventEntity();
        try {
            String payload = mapper.writeValueAsString(event);
            outboxEvent.setAggregateId(event.productId());
            outboxEvent.setAggregateType("product");
            outboxEvent.setEventType(EventType.STOCK_UNIT_CREATED.getValue());
            outboxEvent.setPayload(payload);
            outboxEvent.setPublished(false);
            outboxEvent.setCreatedAt(Instant.now());
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Failed to serialize outbox event " + event.eventId(), ex);
        }
        jpaRepository.save(outboxEvent);
    }

    @Override
    public void markAsProcessed(UUID id) {
        jpaRepository.markAsProcessed(id);
    }

    @Override
    public List<OutboxEventEntity> findUnpublished() {
        return jpaRepository.findByPublishedFalseOrderByCreatedAtAsc();
    }
}