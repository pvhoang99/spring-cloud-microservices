package com.example.inventory.dao.repository;

import com.example.inventory.dao.entity.ShipmentEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends Neo4jRepository<ShipmentEntity, Long> {

}
