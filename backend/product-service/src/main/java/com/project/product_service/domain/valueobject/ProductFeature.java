package com.project.product_service.domain.valueobject;

public record ProductFeature(Integer volumeInMl, Integer weightInGram) {
    
    public ProductFeature {
        if (volumeInMl == null || volumeInMl <= 0) {
            throw new IllegalArgumentException("Product volume must be greater than 0");
        }
        if (weightInGram == null || weightInGram <= 0) {
            throw new IllegalArgumentException("Product weight must be greater than 0");
        }
    }
}
