package com.example.auth.command.authentication.handler;

import com.example.auth.command.authentication.LoginCommand;
import com.example.common.config.CommonResult;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@Service
@RequiredArgsConstructor
public class AuthenticationHandler {

  private final TokenEndpoint tokenEndpoint;

  @CommandHandler
  public CommonResult<?> handle(LoginCommand command) {
    Map<String, String> parameters = new LinkedHashMap<>() {{
      put("username", command.getUsername());
      put("password", command.getPassword());
      put("grant_type", "password");
      put("client_id", "hoang");
      put("client_secret", "1");
    }};
    try {
      ResponseEntity<OAuth2AccessToken> response = tokenEndpoint.postAccessToken(
          new UsernamePasswordAuthenticationToken("hoang", null, new ArrayList<>()),
          parameters);
      OAuth2AccessToken accessToken = response.getBody();
      assert accessToken != null;
      return CommonResult.success(accessToken);
    } catch (HttpRequestMethodNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

}
