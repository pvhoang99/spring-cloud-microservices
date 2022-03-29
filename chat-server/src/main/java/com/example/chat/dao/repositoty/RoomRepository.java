package com.example.chat.dao.repositoty;

import com.example.chat.dao.entity.RoomEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends Neo4jRepository<RoomEntity, Long> {

}
