package com.example.oauth2client.controller;

import com.example.oauth2client.service.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class OAuth2Controller {

  private static final String CLIENT_ID = "hoang";
  private static final String CLIENT_SECRET = "1";
  private static final String REDIRECT_URI = "http://localhost:8182/oauth/callback";

  @Autowired
  private OAuth2Service oAuth2Service;

  @GetMapping("/callback")
  public ResponseEntity oauthCallback(@RequestParam Map<String, String> requestParam) {
    if (requestParam == null || requestParam.isEmpty()) {
      Map<String, Object> response = new HashMap<>();
      response.put("error", "invalid_response_type");
      response.put("error_description", "Server not supported for response_type = token");
      return ResponseEntity.ok(response);
    } else if (requestParam.containsKey("error")) {
      Map<String, Object> error = new HashMap<>();
      error.put("error", requestParam.get("error"));
      error.put("error_description", requestParam.get("error_description"));
      return ResponseEntity.ok(error);
    }
    return ResponseEntity.ok(oAuth2Service
        .getAccessToken(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI, requestParam.get("code"),
            requestParam.get("state")));
  }
}
