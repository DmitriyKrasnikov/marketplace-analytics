package com.krasnikov.apiservices.model.product;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString(exclude = {"description", "images", "specifications"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {

    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(length = 1000)  // Явное указание длины для названия
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Price price;

    @Column(length = 200)  // Ограничение длины для категории
    private String category;

    @Column(length = 100)  // Ограничение длины для бренда
    private String brand;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Stock stock;

    @Column(length = 100)  // Ограничение длины для SKU
    private String sku;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> tags;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<Image> images;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, String> specifications;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "product_index", length = 100)  // Ограничение длины
    private String index;

    @Column(name = "store_id", length = 100)  // Ограничение длины
    private String storeId;

    // Вложенные классы - НЕ Embeddable, так как хранятся как JSONB
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Price {
        private Double amount;

        @Column(length = 3)  // Фиксированная длина для валюты (USD, RUB и т.д.)
        private String currency;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Stock {
        private Integer available;
        private Integer reserved;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Image {
        @Column(length = 2048)  // Длинные URL
        private String url;
        private String alt;
    }
}