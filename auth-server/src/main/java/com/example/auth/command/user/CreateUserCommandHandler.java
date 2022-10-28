package com.example.auth.command.user;

import com.example.auth.domain.user.User;
import com.example.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserCommandHandler {

  private final Repository<User> userAggregateRepository;

  @CommandHandler
  public String create(CreateUserCommand command, UserService userService) throws Exception {
    Aggregate<User> userAggregate = userAggregateRepository.newInstance(
        () -> new User(
            command.getUsername(),
            command.getPassword(),
            command.getFullName(),
            command.getEmail(),
            command.getImage(),
            userService
        ));
    return userAggregate.identifierAsString();
  }

}
