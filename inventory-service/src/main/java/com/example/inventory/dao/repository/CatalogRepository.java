package com.example.inventory.dao.repository;

import com.example.inventory.dao.entity.CatalogEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface CatalogRepository extends Neo4jRepository<CatalogEntity, Long> {

}
