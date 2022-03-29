package com.example.chat.dao.entity;

import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Transient;

@NodeEntity(value = "group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @Transient
  private Long userId;

  @Relationship(type = "CONTAIN")
  private List<UserEntity> members = new LinkedList<>();
}
