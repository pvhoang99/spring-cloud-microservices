package com.example.chat.dao.entity;

import lombok.Data;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
@Data
public class RankedUser {

  private UserEntity user;
  private Integer weight;

}
