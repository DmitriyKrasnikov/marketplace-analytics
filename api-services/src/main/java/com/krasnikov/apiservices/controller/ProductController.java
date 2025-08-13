package com.krasnikov.apiservices.controller;

import com.krasnikov.apiservices.model.product.ProductEntity;
import com.krasnikov.apiservices.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<List<ProductEntity>> searchProducts(
            @RequestParam("name") String name
    ) {
        List<ProductEntity> products = productService.searchProductsByName(name);

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(products);
    }
}