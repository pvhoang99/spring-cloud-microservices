package com.example.auth.api.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerV1 {

  private final ConsumerTokenServices tokenServices;

  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public ResponseEntity<?> revokeToken(@RequestParam(value = "token") String token) {
    return ResponseEntity.ok(tokenServices.revokeToken(token));
  }
}
