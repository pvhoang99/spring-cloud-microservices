package com.example.inventory.domain.product;

public interface ProductRepository {

    void save(Product product);

    Product get(String code);

}
