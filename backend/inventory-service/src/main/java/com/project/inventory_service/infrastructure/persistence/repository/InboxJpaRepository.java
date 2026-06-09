package com.project.inventory_service.infrastructure.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.inventory_service.infrastructure.persistence.entity.InboxEventEntity;

public interface InboxJpaRepository extends JpaRepository<InboxEventEntity, UUID> {
}
