package com.example.inventory.infrastructure.repository.mysql;

import com.example.inventory.domain.product.Product;
import com.example.inventory.domain.product.ProductRepository;
import com.example.inventory.infrastructure.repository.jpa.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MysqlProductRepository implements ProductRepository {

  private final JpaProductRepository jpaRepository;

  @Override
  public void save(Product product) {
    this.jpaRepository.save(product);
  }

  @Override
  public Product get(String code) {
    return this.jpaRepository.getOne(code);
  }

}
