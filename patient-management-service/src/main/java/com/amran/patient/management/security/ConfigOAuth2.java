//package com.amran.patient.management.security;
//
//import java.util.Arrays;
//import java.util.List;
//import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
//import org.springframework.security.oauth2.common.AuthenticationScheme;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ConfigOAuth2 implements OAuth2ProtectedResourceDetails {
//
//  @Override
//  public String getId() {
//    return "ehealth";
//  }
//
//  @Override
//  public String getClientId() {
//    return "kidclient";
//  }
//
//  @Override
//  public String getAccessTokenUri() {
//    return "localhost:8080/oauth/token";
//  }
//
//  @Override
//  public boolean isScoped() {
//    return true;
//  }
//
//  @Override
//  public List<String> getScope() {
//    return Arrays.asList("read");
//  }
//
//  @Override
//  public boolean isAuthenticationRequired() {
//    return true;
//  }
//
//  @Override
//  public String getClientSecret() {
//    return "admin";
//  }
//
//  @Override
//  public AuthenticationScheme getClientAuthenticationScheme() {
//    return AuthenticationScheme.header;
//  }
//
//  @Override
//  public String getGrantType() {
//    return "client_credentials";
//  }
//
//  @Override
//  public AuthenticationScheme getAuthenticationScheme() {
//    return AuthenticationScheme.header;
//  }
//
//  @Override
//  public String getTokenName() {
//    return "access_token";
//  }
//
//  @Override
//  public boolean isClientOnly() {
//    return true;
//  }
//}
