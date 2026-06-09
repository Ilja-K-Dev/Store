package com.project.product_service.infrastructure.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.project.product_service.application.command.CreateProductCommand;
import com.project.product_service.application.handler.CreateProductCommandHandler;
import com.project.product_service.domain.exception.CategoryNotFoundException;
import com.project.product_service.domain.exception.InvalidProductDataException;
import com.project.product_service.domain.exception.ProductAlreadyExistsException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductListener {
    
    private final CreateProductCommandHandler handler;

    @KafkaListener(topics = "create-product", groupId = "product-service", properties = {
            "spring.json.value.default.type=com.project.product_service.application.command.CreateProductCommand"
    })
    public void consume(CreateProductCommand command, Acknowledgment ack) {
        try {
            handler.handle(command);

            ack.acknowledge();
        } catch (CategoryNotFoundException
                | ProductAlreadyExistsException
                | InvalidProductDataException ex) {
            log.warn(ex.getMessage());
            ack.acknowledge();
        } catch (Exception ex) {
            log.error("Unexpected error", ex);
        }
    }
}