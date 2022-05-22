package com.example.chat.dao.repository;

import com.example.chat.dao.entity.RankedUser;
import com.example.chat.dao.entity.UserEntity;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findByUserId(Long userId);

  boolean existsByUserId(Long userId);

  @Query("MATCH (userA:USER) , (userB:USER) "
      + " WHERE userA.userId = $fromId and userB.userId = $toId "
      + " CREATE (userA) -[r:FRIEND {since : $since}]->(userB) ")
  void addFriend(@Param("fromId") Long fromId, @Param("toId") Long toId, @Param("since") Date date);

  @Query("MATCH (userA:USER)-[r:FRIEND]-(userB:USER) "
      + "WHERE userA.userId = $fromId and userB.userId = $toId "
      + "DELETE r")
  void removeFriend(@Param("fromId") Long fromId, @Param("toId") Long toId);

  @Query("MATCH (userA:USER), (userB:USER) " +
      "WHERE userA.userId = $userId AND userB.userId = $friendId" +
      "MATCH (userA)-[:FRIEND]-(fof:User)-[:FRIEND]-(userB) " +
      "RETURN DISTINCT fof")
  Streamable<UserEntity> mutualFriend(Long userId, Long friendId);

  @Query("MATCH (me:User {userId: $userId}})-[:FRIEND]-(friends), " +
      "(nonFriend:User)-[:FRIEND]-(friends) " +
      "WHERE NOT (me)-[:FRIEND]-(nonFriend) " +
      "WITH nonFriend, count(nonFriend) as mutualFriends " +
      "RETURN nonFriend as User, mutualFriends as Weight " +
      "ORDER BY Weight DESC")
  Streamable<RankedUser> recommendedFriends(Long userId);

  @Query("MATCH (user:USER),(userB:USER {userId:$me} ) "
      + "WHERE NOT (userB)-[:FRIEND]-(user) and user.userId <> $me "
      + "RETURN user")
  Streamable<UserEntity> getUserIsNotFriend(Long me);

  @Query("MATCH (user:USER),(userB:USER {userId:$me} ) "
      + "WHERE (userB)-[:FRIEND]-(user) and user.userId <> $me "
      + "RETURN user")
  Streamable<UserEntity> getUserIsFriend(Long me);
}
