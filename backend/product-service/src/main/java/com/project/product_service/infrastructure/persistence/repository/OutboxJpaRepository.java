package com.project.product_service.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.project.product_service.infrastructure.persistence.entity.OutboxEventEntity;

public interface OutboxJpaRepository extends JpaRepository<OutboxEventEntity, UUID> {

    @Query(value = """
                SELECT *
                FROM product.outbox_event
                WHERE published = false
                ORDER BY created_at
                FOR UPDATE SKIP LOCKED
                LIMIT :limit
            """, nativeQuery = true)
    List<OutboxEventEntity> claimBatch(@Param("limit") int limit);

    @Modifying
    @Transactional
    @Query("UPDATE OutboxEventEntity e SET e.published = true WHERE e.id = :id")
    void markAsProcessed(@Param("id") UUID id);
}
