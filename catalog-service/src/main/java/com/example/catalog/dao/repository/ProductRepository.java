package com.example.catalog.dao.repository;

import com.example.catalog.dao.entity.Product;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductRepository extends Neo4jRepository<Product, Long> {

}
