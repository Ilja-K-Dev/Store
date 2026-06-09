package com.project.product_service.domain.exception;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(String message) {
        super("Product already exists: " + message);
    }
}
