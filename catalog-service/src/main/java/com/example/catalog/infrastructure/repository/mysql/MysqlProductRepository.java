package com.example.catalog.infrastructure.repository.mysql;

import com.example.catalog.domain.product.Product;
import com.example.catalog.domain.product.ProductRepository;
import com.example.catalog.infrastructure.repository.jpa.JpaProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MysqlProductRepository implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public void save(Product product) {
        this.jpaProductRepository.save(product);
    }

}
