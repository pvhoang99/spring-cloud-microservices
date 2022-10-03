package com.example.auth.api.v1;

import com.example.auth.command.role.CreateRoleCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/role")
@RequiredArgsConstructor
public class RoleControllerV1 {

  private final RoleServiceV1 roleServiceV1;
  private final CommandGateway commandGateway;

  @GetMapping
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(roleServiceV1.getAll());
  }

  @PostMapping
  public ResponseEntity<?> save(@RequestBody CreateRoleCommand createRoleCommand) {
    commandGateway.sendAndWait(createRoleCommand);
    return ResponseEntity.ok().build();
  }

}
