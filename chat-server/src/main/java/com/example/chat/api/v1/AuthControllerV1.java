package com.example.chat.api.v1;

import com.example.chat.dto.LoginByCodeDTO;
import com.example.chat.service.AuthServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthControllerV1 {

  private final AuthServiceV1 authServiceV1;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginByCodeDTO loginByCodeDTO) {
    return ResponseEntity.ok(authServiceV1.loginByCode(loginByCodeDTO));
  }

}
