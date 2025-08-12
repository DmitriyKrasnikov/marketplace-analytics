package com.krasnikov.apiservices.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class ProductProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public CompletableFuture<SendResult<String, String>> sendProduct(Product product) {
        String productId = product.getProductId();
        String topic = "products";

        try {
            String json = objectMapper.writeValueAsString(product);

            log.info("Sending product to Kafka [id: {}, name: {}, topic: {}]",
                    productId, product.getName(), topic);

            CompletableFuture<SendResult<String, String>> future =
                    kafkaTemplate.send(topic, productId, json);

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
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize product to JSON [id: {}, name: {}]",
                    productId, product.getName(), e);
            CompletableFuture<SendResult<String, String>> failedFuture = new CompletableFuture<>();
            failedFuture.completeExceptionally(e);
            return failedFuture;
        }
    }
}