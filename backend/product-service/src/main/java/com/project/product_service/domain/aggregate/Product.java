package com.project.product_service.domain.aggregate;

import java.util.UUID;

import com.project.product_service.domain.exception.InvalidProductDataException;
import com.project.product_service.domain.valueobject.ProductFeature;
import com.project.product_service.domain.valueobject.ProductName;

import lombok.Getter;

@Getter
public class Product {

    private UUID id;
    private ProductName productName;
    private ProductFeature productFeature;
    private UUID categoryId;

    private Product(
            UUID id,
            String productName,
            ProductFeature feature,
            UUID categoryId) {

        this.id = id;
        this.productName = new ProductName(productName);
        this.productFeature = feature;
        this.categoryId = categoryId;
    }

    public static Product create(
            String productName,
            Integer volumeInMl,
            Integer weightInGram,
            UUID categoryId) {
        try {
            ProductFeature feature = new ProductFeature(volumeInMl, weightInGram);

            return new Product(
                    UUID.randomUUID(),
                    productName,
                    feature,
                    categoryId);
        } catch (IllegalArgumentException ex) {
            throw new InvalidProductDataException(ex.getMessage());
        }

    }

    public static Product restore(
            UUID id,
            String productName,
            Integer volumeInMl,
            Integer weightInGram,
            UUID categoryId) {
        try {
            ProductFeature feature = new ProductFeature(volumeInMl, weightInGram);

            return new Product(
                    id,
                    productName,
                    feature,
                    categoryId);
        } catch (IllegalArgumentException ex) {
            throw new InvalidProductDataException(ex.getMessage());
        }
    }

    public void rename(ProductName newName) {
        this.productName = newName;
    }

    public void updateFeature(ProductFeature feature) {
        this.productFeature = feature;
    }
}
