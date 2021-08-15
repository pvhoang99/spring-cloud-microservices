package com.amran.clinic.management.security;

import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

public class Oauth2Config extends BaseOAuth2ProtectedResourceDetails {

  @Override
  public void setAccessTokenUri(String accessTokenUri) {
    super.setAccessTokenUri("http://ehealth-api-gateway/oauth/token");
  }

  @Override
  public void setClientId(String clientId) {
    super.setClientId("kidclient");
  }
}
