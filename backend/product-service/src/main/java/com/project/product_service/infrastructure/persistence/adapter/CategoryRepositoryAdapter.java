package com.project.product_service.infrastructure.persistence.adapter;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.project.product_service.domain.repository.CategoryRepository;
import com.project.product_service.infrastructure.persistence.repository.CategoryJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryAdapter implements CategoryRepository {
    
    private final CategoryJpaRepository jpaRepository;

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }
}