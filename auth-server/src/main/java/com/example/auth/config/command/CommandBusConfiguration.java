package com.example.auth.config.command;

import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandBusConfiguration {

  @Autowired
  public void configCommandBus(CommandBus commandBus) {
    commandBus.registerDispatchInterceptor(new MyCommandDispatchInterceptor());
  }

}
