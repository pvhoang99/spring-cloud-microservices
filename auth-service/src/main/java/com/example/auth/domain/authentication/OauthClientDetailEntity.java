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
//@Table(name = "oauth_client_details")
//public class OauthClientDetailEntity implements Serializable {
//
//  @Id
//  @Column(name = "client_id")
//  private String clientId;
//
//  @Column(name = "resource_ids")
//  private String resourceIds;
//
//  @Lob
//  @Column(name = "client_secret", length = 4000)
//  private String clientSecret;
//
//  private String scope;
//
//  @Column(name = "authorized_grant_types")
//  private String authorizedGrantTypes;
//
//  @Column(name = "web_server_redirect_uri", length = 1000)
//  private String webServerRedirectUri;
//
//  private String authorities;
//
//  @Column(name = "access_token_validity")
//  private Integer accessTokenValidity;
//
//  @Column(name = "refresh_token_validity")
//  private Integer refreshTokenValidity;
//
//  @Column(name = "additional_information")
//  private String additionalInformation;
//
//  private boolean autoApprove = true;
//
//}
