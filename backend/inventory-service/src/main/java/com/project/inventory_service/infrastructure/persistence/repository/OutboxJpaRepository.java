package com.project.inventory_service.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.project.inventory_service.infrastructure.persistence.entity.OutboxEventEntity;

public interface OutboxJpaRepository extends JpaRepository<OutboxEventEntity, UUID> {
    List<OutboxEventEntity> findByPublishedFalseOrderByCreatedAtAsc();

    @Modifying
    @Transactional
    @Query("UPDATE OutboxEventEntity e SET e.published = true WHERE e.id = :id")
    void markAsProcessed(@Param("id") UUID id);
}
