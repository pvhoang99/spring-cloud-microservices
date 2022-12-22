package com.example.auth.command.user;

import com.example.auth.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserCommandHandler {

    private final Repository<User> userAggregateRepository;

    @CommandHandler
    public void update(UpdateUserCommand command) {
        Aggregate<User> userAggregate = userAggregateRepository.load(command.getId());
        userAggregate.execute(u -> u.update(
            command.getPassword(),
            command.getFullName(),
            command.getEmail(),
            command.getImage()));
    }

}
