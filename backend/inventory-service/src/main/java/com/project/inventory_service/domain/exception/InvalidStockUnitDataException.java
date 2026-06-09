package com.project.inventory_service.domain.exception;

public class InvalidStockUnitDataException extends RuntimeException {
    public InvalidStockUnitDataException(String message) {
        super("Stock unit has invalid data: " + message);
    }

}
