package com.example.auth.command.user.handler;

import com.example.auth.command.user.CreateUserCommand;
import com.example.auth.command.user.UpdateUserCommand;
import com.example.auth.domain.role.Role;
import com.example.auth.domain.user.User;
import com.example.auth.repository.RoleRepository;
import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandHandler {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @CommandHandler
  public void handle(CreateUserCommand command) {

    this.checkUsernameExisted(command.getUsername());
    Role role = roleRepository.findById(command.getRoleId()).orElseThrow(() -> new RuntimeException("Role k ton tai"));
    User user = User.create(command.getUsername(),
        command.getPassword(),
        command.getFullName(),
        command.getEmail(),
        command.getImage(),
        role);

    userRepository.save(user);
  }

  private void checkUsernameExisted(String username) {
    if (userRepository.existsByUsername(username)) {
      throw new RuntimeException("Username existed");
    }
  }

  @CommandHandler
  public void handle(UpdateUserCommand command) {

  }

}
