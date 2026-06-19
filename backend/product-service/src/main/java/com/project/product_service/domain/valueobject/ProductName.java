package com.project.product_service.domain.valueobject;

public record ProductName(String value) {
    
    public ProductName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Product name is empty");
        }
    }
}
