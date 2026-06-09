package com.project.product_service.application.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.product_service.application.command.CreateProductCommand;
import com.project.product_service.domain.aggregate.Product;
import com.project.product_service.domain.exception.CategoryNotFoundException;
import com.project.product_service.domain.exception.ProductAlreadyExistsException;
import com.project.product_service.domain.repository.CategoryRepository;
import com.project.product_service.domain.repository.OutboxRepository;
import com.project.product_service.domain.repository.ProductRepository;
import com.project.shared.event.ProductCreatedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateProductCommandHandler {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OutboxRepository outboxRepository;

    @Transactional
    public void handle(CreateProductCommand cmd) {
        if (!categoryRepository.existsById(cmd.categoryId())) {
            throw new CategoryNotFoundException(cmd.categoryId().toString());
        }

        if (productRepository.findByName(cmd.displayName()).isPresent()) {
            throw new ProductAlreadyExistsException(cmd.displayName());
        }

        Product product = Product.create(cmd.displayName(), cmd.volumeInMl(), cmd.weightInGram(), cmd.categoryId());
        productRepository.save(product);

        ProductCreatedEvent event = ProductCreatedEvent.from(product.getId());
        outboxRepository.save(event);
    }
}
