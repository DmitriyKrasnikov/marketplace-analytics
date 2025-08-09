package com.krasnikov.apiservices.service.file;

import com.krasnikov.apiservices.model.product.Product;
import com.krasnikov.apiservices.service.parser.ProductParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ProductFileReader {
    private final ProductParser parser;

    public List<Product> readProducts(Path filePath) {
        try (Stream<String> lines = Files.lines(filePath)) {
            return lines.map(parser::parseFromString)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read products file: " + filePath, e);
        }
    }
}