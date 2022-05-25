package com.example.catalog.dao.repository;

import com.example.catalog.dao.entity.File;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends Neo4jRepository<File, String> {

}
