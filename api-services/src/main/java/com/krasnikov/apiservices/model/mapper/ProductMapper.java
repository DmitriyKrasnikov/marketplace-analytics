package com.krasnikov.apiservices.model.mapper;

/*mport com.krasnikov.apiservices.model.avro.Image;
import com.krasnikov.apiservices.model.avro.Price;
import com.krasnikov.apiservices.model.avro.ProductAvro;
import com.krasnikov.apiservices.model.avro.Stock;
import com.krasnikov.apiservices.model.product.ProductEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;*/

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

/*    public ProductAvro toAvro(ProductEntity entity) {
        return ProductAvro.newBuilder()
                .setProductId(entity.getProductId())
                .setName(entity.getName())
                .setDescription(entity.getDescription())
                .setPrice(toAvroPrice(entity.getPrice()))
                .setCategory(entity.getCategory())
                .setBrand(entity.getBrand())
                .setStock(toAvroStock(entity.getStock()))
                .setSku(entity.getSku())
                .setTags(entity.getTags())
                .setImages(toAvroImages(entity.getImages()))
                .setSpecifications(entity.getSpecifications())
                .setCreatedAt(toEpochMillis(entity.getCreatedAt()))
                .setUpdatedAt(toEpochMillis(entity.getUpdatedAt()))
                .setIndex(entity.getIndex())
                .setStoreId(entity.getStoreId())
                .build();
    }

    private Price toAvroPrice(ProductEntity.Price price) {
        return Price.newBuilder()
                .setAmount(price.getAmount())
                .setCurrency(price.getCurrency())
                .build();
    }

    private Stock toAvroStock(ProductEntity.Stock stock) {
        return Stock.newBuilder()
                .setAvailable(stock.getAvailable())
                .setReserved(stock.getReserved())
                .build();
    }

    private List<Image> toAvroImages(List<ProductEntity.Image> images) {
        return images.stream()
                .map(this::toAvroImage)
                .collect(Collectors.toList());
    }

    private Image toAvroImage(ProductEntity.Image image) {
        return Image.newBuilder()
                .setUrl(image.getUrl())
                .setAlt(image.getAlt())
                .build();
    }

    private long toEpochMillis(Instant instant) {
        return instant != null ? instant.toEpochMilli() : 0;
    }
 */
}
