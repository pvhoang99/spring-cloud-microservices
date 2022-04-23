package com.example.chat.dao.repository;

import com.example.chat.dao.entity.MessageEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends Neo4jRepository<MessageEntity, Long> {

  @Query("MATCH (userA:USER)-[]-"
      + "(message:message)-[]-(userB:USER) "
      + "WHERE (userA.userId=$userId AND userB.userId=$friendId) "
      + "return (message) order by (message.createdAt)")
  Streamable<MessageEntity> oldMessage(Long userId, Long friendId);
}
