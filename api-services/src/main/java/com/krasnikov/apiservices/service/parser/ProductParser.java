package com.krasnikov.apiservices.service.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krasnikov.apiservices.model.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductParser {
    private final ObjectMapper objectMapper;

    public Product parseFromString(String json) {
        try {
            return objectMapper.readValue(json, Product.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse product JSON: " + json, e);
        }
    }
}