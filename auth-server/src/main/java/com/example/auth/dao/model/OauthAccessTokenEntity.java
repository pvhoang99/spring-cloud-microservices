package com.example.auth.dao.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Md. Amran Hossain
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_access_token", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"authentication", "authentication_id"})})
public class OauthAccessTokenEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "token_id")
  private String tokenId;

  @Lob
  private byte[] token;

  @Column(name = "authentication_id")
  private String authenticationId;

  @Column(name = "user_name", unique = true)
  @NotNull
  private String userName;

  @Column(name = "client_id")
  private String clientId;

  @Lob
  private byte[] authentication;

  @Column(name = "refresh_token")
  private String refreshToken;
}
