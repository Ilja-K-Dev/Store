package com.project.inventory_service.infrastructure.kafka.listener;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.project.inventory_service.application.command.CreateStockUnitCommand;
import com.project.inventory_service.application.handler.CreateStockUnitCommandHandler;
import com.project.inventory_service.domain.exception.InvalidStockUnitDataException;
import com.project.inventory_service.domain.exception.NoSpaceAvailableException;
import com.project.inventory_service.domain.repository.InboxRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockUnitListener {

    private final CreateStockUnitCommandHandler handler;
    private final InboxRepository inboxRepository;

    @KafkaListener(topics = "product-created", groupId = "inventory-service", properties = {
            "spring.json.value.default.type=com.project.inventory_service.application.command.CreateStockUnitCommand"
    })
    public void consume(CreateStockUnitCommand cmd, Acknowledgment ack) {
        if (trySave(cmd)) {
            try {
                handler.handle(cmd);

                ack.acknowledge();
            } catch (NoSpaceAvailableException | InvalidStockUnitDataException ex) {
                log.warn(ex.getMessage());
                ack.acknowledge();
            } catch (Exception ex) {
                log.error("Unexpected error", ex);
            }
        }
    }

    private boolean trySave(CreateStockUnitCommand cmd) {
        try {
            inboxRepository.save(cmd);
            return true;
        } catch (DataIntegrityViolationException ex) {
            return false;
        }
    }
}