package com.project.inventory_service.infrastructure.kafka.publisher;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.inventory_service.domain.event.StockUnitCreatedEvent;
import com.project.inventory_service.infrastructure.persistence.entity.OutboxEventEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OutboxPublisher {
    
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void publish(OutboxEventEntity event) {
        try {
            StockUnitCreatedEvent payload = objectMapper.readValue(event.getPayload(), StockUnitCreatedEvent.class);
            kafkaTemplate.send(event.getEventType(), event.getId().toString(), payload);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Failed to deserialize outbox event " + event.getId(), ex);
        }
    }
}
