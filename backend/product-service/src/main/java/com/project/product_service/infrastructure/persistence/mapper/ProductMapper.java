package com.project.product_service.infrastructure.persistence.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.project.product_service.domain.aggregate.Product;
import com.project.product_service.infrastructure.persistence.entity.ProductEntity;

@Component
public class ProductMapper {

    public ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();

        entity.setDisplayName(product.getProductName().value());
        entity.setVolumeInMl(product.getProductFeature().volumeInMl());
        entity.setWeightInGram(product.getProductFeature().weightInGram());
        entity.setCategoryId(product.getCategoryId());
        entity.setCreatedAt(Instant.now());

        return entity;
    }

    public Product toAggregate(ProductEntity entity) {
        return Product.restore(
                entity.getId(),
                entity.getDisplayName(),
                entity.getVolumeInMl(),
                entity.getWeightInGram(),
                entity.getCategoryId());
    }
}