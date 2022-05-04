package com.example.inventory.dao.repository;

import com.example.inventory.dao.entity.ProductEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends Neo4jRepository<ProductEntity, Long> {

  ProductEntity getProductEntityByProductId(String productId);
}
