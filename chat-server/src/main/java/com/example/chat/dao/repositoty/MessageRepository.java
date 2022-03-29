package com.example.chat.dao.repositoty;

import com.example.chat.dao.entity.MessageEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends Neo4jRepository<MessageEntity, Long> {

}
