package com.project.product_service.domain.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message) {
        super("Category not found: " + message);
    }
}
