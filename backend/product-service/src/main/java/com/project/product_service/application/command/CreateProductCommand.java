package com.project.product_service.application.command;

import java.util.UUID;

public record CreateProductCommand(
        String displayName,
        Integer volumeInMl,
        Integer weightInGram,
        UUID categoryId) {
}