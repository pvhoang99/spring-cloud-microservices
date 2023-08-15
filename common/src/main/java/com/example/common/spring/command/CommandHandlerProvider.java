package com.example.common.spring.command;

import com.example.common.command.CommandHandler;
import org.springframework.context.ApplicationContext;

public class CommandHandlerProvider<H extends CommandHandler<?, ?>> {

  private final ApplicationContext applicationContext;
  private final Class<H> type;

  protected CommandHandlerProvider(ApplicationContext applicationContext, Class<H> type) {
    this.applicationContext = applicationContext;
    this.type = type;
  }

  public H get() {
    return this.applicationContext.getBean(type);
  }

}
