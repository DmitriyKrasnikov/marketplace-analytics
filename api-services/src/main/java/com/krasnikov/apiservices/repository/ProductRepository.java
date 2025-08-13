package com.krasnikov.apiservices.repository;

import com.krasnikov.apiservices.model.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    List<ProductEntity> findByName(String name);

    List<ProductEntity> findByNameContainingIgnoreCase(String namePart);
}