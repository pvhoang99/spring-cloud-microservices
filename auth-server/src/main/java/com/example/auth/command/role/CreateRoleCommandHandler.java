package com.example.auth.command.role;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateRoleCommandHandler {

  @CommandHandler
  public void handle(CreateRoleCommand createRoleCommand) {
    log.info("handle");
  }

}
