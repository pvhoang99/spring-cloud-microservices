package com.example.inventory.dao.repository;

import com.example.inventory.dao.entity.WarehouseEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends Neo4jRepository<WarehouseEntity, Long> {

}
