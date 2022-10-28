package com.example.auth.api.v1;

import com.example.auth.command.user.CreateUserCommand;
import com.example.auth.command.user.UpdateUserCommand;
import com.example.common.api.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final CommandGateway commandGateway;

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody final CreateUserCommand command) {
    String userId = this.commandGateway.sendAndWait(command);

    return ResponseEntity.ok(CommonResult.success(userId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody final UpdateUserCommand command) {
    command.setId(id);
    this.commandGateway.sendAndWait(command);

    return ResponseEntity.ok(CommonResult.success());
  }

}
