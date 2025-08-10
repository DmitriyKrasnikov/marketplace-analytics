package com.krasnikov.apiservices.service;

import com.krasnikov.apiservices.model.product.Product;
import com.krasnikov.apiservices.service.file.ProductFileReader;
import com.krasnikov.apiservices.service.kafka.ProductProducer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopApiEmulationService {
    private final ProductFileReader fileReader;
    private final ProductProducer producer;

    @Value("${product.initialization.file:data/products.json}")
    private String initializationFile;

    @Value("${product.initialization.enabled:true}")
    private boolean initializationEnabled;

    @PostConstruct
    public void init() {
        if (!initializationEnabled) return;

        try {
            ClassPathResource resource = new ClassPathResource(initializationFile);
            if (!resource.exists()) {
                log.warn("Initialization file not found: {}", initializationFile);
                return;
            }

            Path filePath = Path.of(resource.getURI());
            log.info("Starting product import from: {}", filePath);
            importProductsFromFile(filePath);
        } catch (Exception e) {
            log.error("Product initialization failed", e);
        }
    }

    public void importProductsFromFile(Path filePath) {
        List<Product> products = fileReader.readProducts(filePath);
        log.info("Loaded {} products from file", products.size());

        products.forEach(producer::sendProduct);

        log.info("Initiated sending of {} products to Kafka", products.size());
    }
}