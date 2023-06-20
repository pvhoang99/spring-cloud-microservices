package com.example.auth.api.v1;

import com.example.auth.command.user.CreateUserCommand;
import com.example.auth.command.user.UpdateUserCommand;
import com.example.common.command.CommandBus;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  private final CommandBus commandBus;

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody final CreateUserCommand command) {
    return new ResponseEntity<>(this.commandBus.execute(command), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateUser(@PathVariable Long id,
      @RequestBody final UpdateUserCommand command) {
    command.setId(id);
    this.commandBus.execute(command);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/me")
  public ResponseEntity<?> currentUser(Principal principal) {

    return null;
  }

}
