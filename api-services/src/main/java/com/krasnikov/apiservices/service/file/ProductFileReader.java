package com.krasnikov.apiservices.service.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.krasnikov.apiservices.model.product.ProductEvent;
import com.krasnikov.apiservices.service.parser.ProductParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ProductFileReader {
    private final ProductParser parser;

    public List<ProductEvent> readProducts(Path filePath) {
        try {
            String content = Files.readString(filePath).trim();
            if (content.isEmpty()) {
                return List.of();
            }

            if (content.startsWith("[")) {
                var mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                return mapper.readValue(content, new TypeReference<>() {
                });
            }

            if (content.startsWith("{")) {
                return List.of(parser.parseFromString(content));
            }

            try (Stream<String> lines = Files.lines(filePath)) {
                return lines
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(parser::parseFromString)
                        .collect(Collectors.toList());
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read products file: " + filePath, e);
        }
    }
}