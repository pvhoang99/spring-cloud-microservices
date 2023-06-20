package com.example.auth.api.v1;

import com.example.auth.command.role.CreateRoleCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/roles")
@RequiredArgsConstructor
public class RoleController {

  @PostMapping
  public ResponseEntity<?> save(@RequestBody CreateRoleCommand createRoleCommand) {
    return ResponseEntity.ok().build();
  }

}
