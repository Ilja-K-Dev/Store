package com.project.product_service.infrastructure.kafka.publisher;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.shared.event.ProductCreatedEvent;
import com.project.product_service.infrastructure.exception.OutboxPublishingException;
import com.project.product_service.infrastructure.persistence.entity.OutboxEventEntity;

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
            ProductCreatedEvent payload = objectMapper.readValue(event.getPayload(), ProductCreatedEvent.class);
            kafkaTemplate.send(event.getEventType(), event.getId().toString(), payload);
        } catch (JsonProcessingException ex) {
            throw new OutboxPublishingException(event.getId().toString());
        }
    }
}
