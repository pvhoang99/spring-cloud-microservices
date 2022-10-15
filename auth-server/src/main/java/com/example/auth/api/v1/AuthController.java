package com.example.auth.api.v1;

import com.example.auth.command.authentication.LoginCommand;
import com.example.common.config.CommonResult;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

  private final CommandGateway commandGateway;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginCommand command) {
    CommonResult<?> result = commandGateway.sendAndWait(command);

    return ResponseEntity.ok(result);
  }
}
