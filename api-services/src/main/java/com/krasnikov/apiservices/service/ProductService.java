package com.krasnikov.apiservices.service;

import com.krasnikov.apiservices.model.product.ProductEntity;
import com.krasnikov.apiservices.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductEntity> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}