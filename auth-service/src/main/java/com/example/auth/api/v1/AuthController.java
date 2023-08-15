package com.example.auth.api.v1;

import com.example.common.command.CommandBus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final CommandBus commandBus;

//  @PostMapping("/login")
//  public ResponseEntity<?> login(@RequestBody LoginCommand command) {
//    OAuth2AccessToken accessToken = this.commandBus.execute(command);
//
//    return ResponseEntity.ok(CommonResult.success(accessToken));
//  }

}
