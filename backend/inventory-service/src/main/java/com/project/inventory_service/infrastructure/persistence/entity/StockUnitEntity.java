package com.project.inventory_service.infrastructure.persistence.entity;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stock_unit", schema = "inventory")
public class StockUnitEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(name = "product_id", columnDefinition = "UUID", nullable = false, unique = true)
    private UUID productId;

    @Column(name = "total_stock")
    private Integer totalStock;

    @Column(name = "reserved_stock")
    private Integer reservedStock;

    @Column(name = "inbound_stock")
    private Integer inboundStock;

    @OneToOne
    @JoinColumn(name = "stock_position_id", unique = true)
    private StockPositionEntity stockPosition;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @Version
    Long version;
}
