package com.example.chat.dao.repositoty;

import com.example.chat.dao.entity.UserEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<UserEntity, Long> {

  boolean existsByUsername(String username);

  UserEntity findByUsername(String username);
}
