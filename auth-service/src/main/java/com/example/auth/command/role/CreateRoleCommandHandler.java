package com.example.auth.command.role;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateRoleCommandHandler {

    public void handle(CreateRoleCommand createRoleCommand) {
        log.info("handle");
    }

}
