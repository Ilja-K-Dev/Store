package com.project.product_service.domain.exception;

public class InvalidProductDataException extends RuntimeException {

    public InvalidProductDataException(String message) {
        super("Product has invalid data: " + message);
    }
}
