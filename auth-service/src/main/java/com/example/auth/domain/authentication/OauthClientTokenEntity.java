//package com.example.auth.domain.authentication;
//
//import java.io.Serializable;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Lob;
//import javax.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "oauth_client_token")
//public class OauthClientTokenEntity implements Serializable {
//
//  @Column(name = "token_id")
//  private String tokenId;
//
//  @Lob
//  private byte[] token;
//
//  @Id
//  @Column(name = "authentication_id")
//  private String authenticationId;
//
//  @Column(name = "user_name")
//  private String userName;
//
//  @Column(name = "client_id")
//  private String clientId;
//
//}
