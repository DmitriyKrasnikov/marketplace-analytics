package com.krasnikov.apiservices.model.product;

import lombok.*;
import com.fasterxml.jackson.annotation.*;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductEvent {
    @JsonProperty("product_id")
    private String productId;

    private String name;
    private String description;

    private Price price;
    private String category;
    private String brand;

    private Stock stock;

    private String sku;
    private List<String> tags;

    private List<Image> images;

    private Map<String, String> specifications;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Instant createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Instant updatedAt;

    private String index;

    @JsonProperty("store_id")
    private String storeId;

    // Вложенные классы
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Price {
        private Double amount;
        private String currency;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Stock {
        private Integer available;
        private Integer reserved;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Image {
        private String url;
        private String alt;
    }
}