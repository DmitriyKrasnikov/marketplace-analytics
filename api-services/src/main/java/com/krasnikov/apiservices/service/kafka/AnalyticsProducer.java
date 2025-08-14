package com.krasnikov.apiservices.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krasnikov.apiservices.model.analytics.AnalyticsEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnalyticsProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendSearchEvent(String userId, String query) {
        AnalyticsEvent event = AnalyticsEvent.builder()
                .userId(userId)
                .eventType("PRODUCT_SEARCH")
                .query(query)
                .timestamp(Instant.now())
                .build();

        sendEvent("product-search-events", event);
    }

    private void sendEvent(String topic, AnalyticsEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(topic, event.getUserId(), json);

            log.info("Sent analytics event to {}: {}", topic, json);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize analytics event", e);
        }
    }
}
