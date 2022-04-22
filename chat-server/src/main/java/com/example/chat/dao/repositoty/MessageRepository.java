package com.example.chat.dao.repositoty;

import com.example.chat.dao.entity.MessageEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends Neo4jRepository<MessageEntity, Long> {

  @Query("MATCH (userA:USER {userId: ?#{#userSend})-[:SEND]->"
      + "(message:message)-[:RECEIVE]->(userB:USER {userId: ?#{#userFrom}) "
      + "return (message) order by (message.createdAt)")
  Streamable<MessageEntity> oldMessage(Long userSend, Long userFrom);
}
