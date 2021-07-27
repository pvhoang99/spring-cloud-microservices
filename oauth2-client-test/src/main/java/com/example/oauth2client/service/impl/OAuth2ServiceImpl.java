package com.example.oauth2client.service.impl;

import com.example.oauth2client.model.AccessToken;
import com.example.oauth2client.service.OAuth2Service;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuth2ServiceImpl implements OAuth2Service {

  @Autowired
  private RestTemplateBuilder restTemplateBuilder;

  @Override
  public AccessToken getAccessToken(String clientId, String clientSecret, String redirectUri,
      String code, String state) {
    String getToken = "http://localhost:8082/oauth/token?redirect_uri=" + redirectUri
        + "&grant_type=authorization_code&code=" +
        code + "&state=" + state;
    HttpHeaders headers = new HttpHeaders();
    String base64Cred = Base64.encodeBase64String((clientId + ":" + clientSecret).getBytes());
    headers.set("Authorization", "Basic " + base64Cred);
    RestTemplate restTemplate = restTemplateBuilder.build();
    HttpEntity request = new HttpEntity(headers);
    ResponseEntity<AccessToken> responseEntity = restTemplate
        .exchange(getToken, HttpMethod.POST, request, AccessToken.class);
    return responseEntity.getBody();
  }
}
