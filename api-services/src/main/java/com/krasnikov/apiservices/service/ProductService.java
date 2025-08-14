package com.krasnikov.apiservices.service;

import com.krasnikov.apiservices.model.product.ProductEntity;
import com.krasnikov.apiservices.repository.ProductRepository;
import com.krasnikov.apiservices.service.kafka.AnalyticsProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final AnalyticsProducer analyticsProducer;

    public List<ProductEntity> searchProductsByName(String name, String userId) {
        analyticsProducer.sendSearchEvent(userId, name);

        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<ProductEntity> getPersonalizedRecommendations(String userId) {
        return productRepository.findTop5ByOrderByCreatedAtDesc();
    }
}