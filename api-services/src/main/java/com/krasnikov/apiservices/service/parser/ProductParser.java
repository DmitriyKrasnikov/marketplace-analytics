package com.krasnikov.apiservices.service.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krasnikov.apiservices.model.product.ProductAvroEventExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductParser {
    private final ObjectMapper objectMapper;

    public ProductAvroEventExample parseFromString(String json) {
        try {
            return objectMapper.readValue(json, ProductAvroEventExample.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse product JSON: " + json, e);
        }
    }
}