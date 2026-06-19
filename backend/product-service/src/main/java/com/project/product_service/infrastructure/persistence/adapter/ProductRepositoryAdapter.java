package com.project.product_service.infrastructure.persistence.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.project.product_service.domain.aggregate.Product;
import com.project.product_service.domain.repository.ProductRepository;
import com.project.product_service.infrastructure.persistence.entity.ProductEntity;
import com.project.product_service.infrastructure.persistence.mapper.ProductMapper;
import com.project.product_service.infrastructure.persistence.repository.ProductJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository jpaRepository;
    private final ProductMapper mapper;

    @Override
    public void save(Product product) {
        ProductEntity entity = mapper.toEntity(product);

        jpaRepository.save(entity);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toAggregate);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return jpaRepository.findByDisplayName(name).map(mapper::toAggregate);
    }
}