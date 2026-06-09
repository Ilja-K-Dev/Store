package com.project.inventory_service.infrastructure.persistence.adapter;

import java.time.Instant;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.inventory_service.application.command.CreateStockUnitCommand;
import com.project.inventory_service.domain.event.EventType;
import com.project.inventory_service.domain.repository.InboxRepository;
import com.project.inventory_service.infrastructure.persistence.entity.InboxEventEntity;
import com.project.inventory_service.infrastructure.persistence.repository.InboxJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InboxRepositoryAdapter implements InboxRepository {

    private final InboxJpaRepository jpaRepository;
    private final ObjectMapper mapper;

    @Override
    public void save(CreateStockUnitCommand cmd) {
        InboxEventEntity inboxEvent = new InboxEventEntity();
        try {
            String payload = mapper.writeValueAsString(cmd);
            inboxEvent.setEventId(cmd.productId());
            inboxEvent.setEventType(EventType.STOCK_UNIT_CREATED.getValue());
            inboxEvent.setPayload(payload);
            inboxEvent.setReceivedAt(Instant.now());
            inboxEvent.setProcessed(false);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Failed to serialize inbox event " + cmd.productId(), ex);
        }
        jpaRepository.save(inboxEvent);
    }
}
