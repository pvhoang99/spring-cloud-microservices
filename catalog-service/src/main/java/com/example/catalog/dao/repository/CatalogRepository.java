package com.example.catalog.dao.repository;

import com.example.catalog.dao.entity.Catalog;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "catalog", path = "catalog")
public interface CatalogRepository extends Neo4jRepository<Catalog, Long> {

  Catalog findByCatalogId(Long catalogId);
}
