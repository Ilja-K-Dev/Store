package com.project.product_service.domain.repository;

import java.util.UUID;

public interface CategoryRepository {

    boolean existsById(UUID id);
}
