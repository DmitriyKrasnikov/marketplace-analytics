package com.krasnikov.apiservices.controller;

import com.krasnikov.apiservices.model.product.ProductEntity;
import com.krasnikov.apiservices.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<List<ProductEntity>> searchProducts(
            @RequestParam("name") String name,
            @RequestHeader("X-User-Id") String userId) {

        List<ProductEntity> products = productService.searchProductsByName(name, userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<ProductEntity>> getRecommendations(
            @RequestHeader("X-User-Id") String userId) {

        List<ProductEntity> recommendations = productService.getPersonalizedRecommendations(userId);
        return ResponseEntity.ok(recommendations);
    }
}