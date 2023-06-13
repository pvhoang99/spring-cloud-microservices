package com.example.auth.command.user;

import com.example.auth.domain.User;
import com.example.auth.repository.UserRepository;
import com.example.auth.service.UserService;
import com.example.common.command.CommandHandler;
import com.example.common.vm.CommandResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserHandler implements CommandHandler<CreateUserCommand, CommandResult<Long>> {

    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public CommandResult<Long> handle(CreateUserCommand command) {
        this.validateUsernameExisted(command.getUsername());
        User user = User.create(
                command.getUsername(),
                command.getPassword(),
                command.getFullName(),
                command.getEmail(),
                command.getImage()
        );
        this.userRepository.save(user);

        return CommandResult.of(user.getId());
    }

    private void validateUsernameExisted(String username) {
        this.userService.checkUserExisted(username);
    }

}
