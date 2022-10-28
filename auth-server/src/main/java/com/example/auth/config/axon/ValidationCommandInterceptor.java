package com.example.auth.config.axon;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import javax.annotation.Nonnull;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidationCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

  private final Validator validator;

  @Nonnull
  @Override
  public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
      @Nonnull List<? extends CommandMessage<?>> messages) {
    return (i, m) -> {
      Set<? extends ConstraintViolation<?>> violations = validator.validate(m.getPayload());
      if (!violations.isEmpty()) {
        throw new ConstraintViolationException(violations);
      }
      return m;
    };
  }
}
