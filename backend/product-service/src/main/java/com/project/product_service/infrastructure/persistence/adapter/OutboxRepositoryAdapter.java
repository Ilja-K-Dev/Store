package com.project.product_service.infrastructure.persistence.adapter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.product_service.domain.event.ProductCreatedEvent;
import com.project.product_service.domain.repository.OutboxRepository;
import com.project.product_service.domain.event.EventType;
import com.project.product_service.infrastructure.exception.OutboxSerializationException;
import com.project.product_service.infrastructure.persistence.entity.OutboxEventEntity;
import com.project.product_service.infrastructure.persistence.repository.OutboxJpaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OutboxRepositoryAdapter implements OutboxRepository {

    private final OutboxJpaRepository jpaRepository;
    private final ObjectMapper mapper;

    @Override
    public void save(ProductCreatedEvent event) {
        try {
            OutboxEventEntity outboxEvent = new OutboxEventEntity();
            String payload = mapper.writeValueAsString(event);
            outboxEvent.setAggregateId(event.productId());
            outboxEvent.setAggregateType("product");
            outboxEvent.setEventType(EventType.PRODUCT_CREATED.getValue());
            outboxEvent.setPayload(payload);
            outboxEvent.setPublished(false);
            outboxEvent.setCreatedAt(Instant.now());
            jpaRepository.save(outboxEvent);
        } catch (JsonProcessingException ex) {
            throw new OutboxSerializationException(event.eventId().toString());
        }
    }

    @Override
    public void markAsProcessed(UUID id) {
        jpaRepository.markAsProcessed(id);
    }

    @Override
    public List<OutboxEventEntity> findUnpublished(Integer limit) {
        return jpaRepository.claimBatch(limit);
    }
}