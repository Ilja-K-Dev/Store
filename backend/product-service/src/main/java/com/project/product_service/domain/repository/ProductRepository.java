package com.project.product_service.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.project.product_service.domain.aggregate.Product;

public interface ProductRepository {

    void save(Product product);

    Optional<Product> findById(UUID id);

    Optional<Product> findByName(String name);
}
