package com.example.chat.dao.repositoty;

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

  boolean existsByUsername(String username);

  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findByUserId(Long userId);

  boolean existsByUserId(Long userId);

  @Query("MATCH (userA:User) , (userB:User)"
      + " WHERE userA.userId = :#{#fromId} and userB.userId = :#{#toId} "
      + " CREATE (userA) -[:FRIEND {since : :#{#since}]->(userB) ")
  void addFriend(@Param("fromId") Long fromId, @Param("toId") Long toId, @Param("since") Date date);

  @Query("MATCH (userA:User)-[r:FRIEND]-(userB:User) "
      + "WHERE userA.userId = :#{#fromId} and userB.userId = :#{#toId} "
      + "REMOVE r")
  void removeFriend(@Param("fromId") Long fromId, @Param("toId") Long toId);

  @Query("MATCH (userA:User), (userB:User) " +
      "WHERE userA.userId={0} AND userB.userId={1} " +
      "MATCH (userA)-[:FRIEND]-(fof:User)-[:FRIEND]-(userB) " +
      "RETURN DISTINCT fof")
  Streamable<UserEntity> mutualFriend(Long fromId, Long toId);

  @Query("MATCH (me:User {userId: {0}})-[:FRIEND]-(friends), " +
      "(nonFriend:User)-[:FRIEND]-(friends) " +
      "WHERE NOT (me)-[:FRIEND]-(nonFriend) " +
      "WITH nonFriend, count(nonFriend) as mutualFriends " +
      "RETURN nonFriend as User, mutualFriends as Weight " +
      "ORDER BY Weight DESC")
  Streamable<RankedUser> recommendedFriends(Long userId);
}
