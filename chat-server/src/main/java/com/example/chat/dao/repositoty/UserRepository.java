package com.example.chat.dao.repositoty;

import com.example.chat.dao.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<UserEntity, Long> {

  boolean existsByUsername(String username);

  Optional<UserEntity> findByUsername(String username);
}
