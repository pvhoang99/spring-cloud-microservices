package com.example.common.spring.command;

import com.example.common.command.Command;
import com.example.common.command.CommandBus;
import com.example.common.command.CommandHandler;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@SuppressWarnings({"unchecked"})
public class SpringCommandBus implements CommandBus {

    private final CommandRegistry registry;
    private final Validator validator;

    @Override
    public <C extends Command<R>, R> R execute(C command) {
        CommandHandler<C, R> commandHandler = (CommandHandler<C, R>) this.registry.get(command.getClass());
        this.validate(command);

        return commandHandler.handle(command);
    }

    private <C extends Command<?>> void validate(C command) {
        Set<ConstraintViolation<C>> violations = this.validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
