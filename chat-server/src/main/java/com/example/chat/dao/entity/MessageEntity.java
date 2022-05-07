package com.example.chat.dao.entity;

import com.example.chat.type.ChatType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.Transient;

@NodeEntity(value = "message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MessageEntity extends BaseEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String text;

  @Transient
  private Long fromUser;

  @Transient
  private Long toUser;

  @Transient
  private Long toGroup;

  @Transient
  private ChatType chatType;

  @Relationship(type = "SEND", direction = Relationship.INCOMING)
  @StartNode
  private UserEntity userSend;

  @Relationship(type = "RECEIVE", direction = Relationship.OUTGOING)
  @EndNode
  private UserEntity userReceive;

  @Relationship(type = "HAVE", direction = Relationship.INCOMING)
  @EndNode
  private RoomEntity roomEntity;

}
