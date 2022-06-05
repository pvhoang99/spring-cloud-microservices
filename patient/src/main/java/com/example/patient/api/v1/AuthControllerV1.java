package com.example.patient.api.v1;

import com.example.patient.dto.LoginByUsernameAndPasswordDTO;
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
  public ResponseEntity<?> loginGrpc(
      @RequestBody LoginByUsernameAndPasswordDTO loginByUsernameAndPasswordDTO) {
    return ResponseEntity.ok(authServiceV1.loginResponse(loginByUsernameAndPasswordDTO));
  }

}
