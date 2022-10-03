package com.example.auth.config.command;

import java.util.List;
import java.util.function.BiFunction;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCommandDispatchInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(MyCommandDispatchInterceptor.class);

  @Override
  public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
    return (index, command) -> {
      LOGGER.info("Dispatching a command {}.", command);
      return command;
    };
  }
}