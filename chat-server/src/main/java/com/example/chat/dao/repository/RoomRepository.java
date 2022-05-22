package com.example.chat.dao.repository;

import com.example.chat.dao.entity.RoomEntity;
import java.util.Set;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends Neo4jRepository<RoomEntity, Long> {

  @Query("MATCH (room:ROOM {id : $roomId) -[r:CONTAIN]-(user:USER)"
      + "WHERE user.userId in $userIds "
      + "DELETE r")
  void removeUser(Set<Long> userIds, Long roomId);

  @Query("MATCH (room:Room) -[r:CONTAIN]-(user:User {user.userId=$userId})")
  Set<RoomEntity> getRoomBelong(Long userId);

}
