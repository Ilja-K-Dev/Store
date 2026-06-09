package com.project.inventory_service.infrastructure.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.inventory_service.domain.repository.OutboxRepository;
import com.project.inventory_service.infrastructure.persistence.entity.OutboxEventEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxPollingWorker {
    
    private final OutboxRepository repository;
    private final OutboxProcessor processor;

    @Scheduled(fixedDelay = 1000)
    public void process() {
        List<OutboxEventEntity> events = repository.findUnpublished();

        for (OutboxEventEntity event : events) {
            try {
                processor.publishEvent(event);
            } catch (Exception ex) {
                log.error("Unexpected error", ex);
            }
        }
    }
}
