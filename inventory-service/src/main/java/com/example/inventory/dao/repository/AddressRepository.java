package com.example.inventory.dao.repository;

import com.example.inventory.dao.entity.AddressEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends Neo4jRepository<AddressEntity, Long> {

}
