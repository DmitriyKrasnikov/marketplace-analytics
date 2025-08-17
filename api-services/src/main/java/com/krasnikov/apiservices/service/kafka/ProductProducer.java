package com.krasnikov.apiservices.service.kafka;

import com.krasnikov.apiservices.model.product.ProductEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductProducer {

    private final KafkaTemplate<String, ProductEvent> kafkaTemplate;

    public CompletableFuture<SendResult<String, ProductEvent>> sendProduct(ProductEvent product) {
        String productId = product.getProductId();
        String topic = "products";

        log.info("Sending product to Kafka [id: {}, name: {}, topic: {}]",
                productId, product.getName(), topic);

        if (product.getCreatedAt() != null) {
            product.setCreatedAt(product.getCreatedAt().withZoneSameInstant(ZoneOffset.UTC));
        }
        if (product.getUpdatedAt() != null) {
            product.setUpdatedAt(product.getUpdatedAt().withZoneSameInstant(ZoneOffset.UTC));
        }

        CompletableFuture<SendResult<String, ProductEvent>> future =
                kafkaTemplate.send(topic, productId, product);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Product sent successfully [id: {}, offset: {}, partition: {}]",
                        productId,
                        result.getRecordMetadata().offset(),
                        result.getRecordMetadata().partition());
            } else {
                log.error("Failed to send product [id: {}, name: {}]: {}",
                        productId, product.getName(), ex.getMessage(), ex);
            }
        });

        return future;
    }
}