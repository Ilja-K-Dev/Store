package com.project.inventory_service.infrastructure.scheduler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.inventory_service.domain.repository.OutboxRepository;
import com.project.inventory_service.infrastructure.kafka.publisher.OutboxPublisher;
import com.project.inventory_service.infrastructure.persistence.entity.OutboxEventEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OutboxProcessor {
    
    private final OutboxRepository repository;
    private final OutboxPublisher publisher;

    @Transactional
    void publishEvent(OutboxEventEntity event) {
        publisher.publish(event);
        repository.markAsProcessed(event.getId());
    }
}
