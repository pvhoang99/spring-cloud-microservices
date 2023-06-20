package com.example.auth.command.authentication;

import com.example.common.command.CommandHandler;
import com.example.common.exception.BadRequestException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@Service
@RequiredArgsConstructor
@Deprecated
public class LoginCommandHandler implements CommandHandler<LoginCommand, OAuth2AccessToken> {

  private final TokenEndpoint tokenEndpoint;

  @Override
  @Transactional
  public OAuth2AccessToken handle(LoginCommand command) {
    Map<String, String> parameters = this.buildParameters(command);
    Principal principal = this.buildDefaultAuthentication();
    try {
      ResponseEntity<OAuth2AccessToken> response = this.tokenEndpoint.postAccessToken(principal,
          parameters);

      return response.getBody();
    } catch (HttpRequestMethodNotSupportedException e) {
      throw new BadRequestException(e.getMessage());
    }
  }

  private Map<String, String> buildParameters(LoginCommand command) {
    return new LinkedHashMap<>() {{
      put("username", command.getUsername());
      put("password", command.getPassword());
      put("grant_type", "password");
      put("client_id", "hoang");
      put("client_secret", "1");
    }};
  }

  private UsernamePasswordAuthenticationToken buildDefaultAuthentication() {
    return new UsernamePasswordAuthenticationToken("hoang", null, new ArrayList<>());
  }

}
