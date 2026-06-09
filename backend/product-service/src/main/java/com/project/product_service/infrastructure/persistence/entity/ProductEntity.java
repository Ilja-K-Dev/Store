package com.project.product_service.infrastructure.persistence.entity;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product", schema = "product")
@Getter
@Setter
public class ProductEntity {
    
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(name = "category_id")
    private UUID categoryId;

    @Column(name = "display_name", length = 200)
    private String displayName;

    @Column(name = "volume_in_ml")
    private Integer volumeInMl;

    @Column(name = "weight_in_gram")
    private Integer weightInGram;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private Instant createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMPTZ")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMPTZ")
    private Instant deletedAt;
}
