package com.example.chat.dao.entity;

import com.example.chat.config.security.user.OAuth2UserInfoFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(value = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String username;

  private String fullName;

  private String email;

  private String token;

  private OAuth2UserInfoFactory.AuthProvider provider;

  private String providerId;

  private String imageUrl;

}
