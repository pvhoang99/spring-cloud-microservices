package com.example.oauth2client.service;

import com.example.oauth2client.model.AccessToken;

public interface OAuth2Service {

  AccessToken getAccessToken(String clientId, String clientSecret, String redirectUri, String code,
      String state);
}
