package com.krasnikov.apiservices.service.kafka;

import com.krasnikov.apiservices.model.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShopApiEmulationProducer {
    private final KafkaTemplate<String, Product> kafkaTemplate;

    public CompletableFuture<SendResult<String, Product>> sendProduct(Product product) {
        String productId = product.getProductId();
        String topic = "products";

        log.info("Sending product to Kafka [id: {}, name: {}, topic: {}]",
                productId, product.getName(), topic);

        CompletableFuture<SendResult<String, Product>> future =
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