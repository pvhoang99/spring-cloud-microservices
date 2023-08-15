package com.example.common.spring.command;

import com.example.common.command.Command;
import com.example.common.command.CommandHandler;
import com.example.common.exception.InternalServerException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings({"unchecked", "rawtypes"})
public class CommandRegistry {

  private static final Logger logger = LoggerFactory.getLogger(CommandRegistry.class);
  private final ApplicationContext applicationContext;
  private final Map<Class<? extends Command>, CommandHandlerProvider> providerMap = new HashMap<>();

  @Autowired
  public CommandRegistry(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
    this.registerHandlers();
  }

  public <C extends Command<R>, R> CommandHandler<C, R> get(Class<C> commandClass) {
    try {
      CommandHandlerProvider<CommandHandler<C, R>> handlerProvider = providerMap.get(commandClass);
      if (handlerProvider == null) {
        throw new InternalServerException("NotFound.CommandHandler");
      }

      return handlerProvider.get();
    } catch (BeansException e) {
      throw new InternalServerException("NotFound.CommandHandler");
    }
  }

  private void registerHandlers() {
    String[] names = this.applicationContext.getBeanNamesForType(CommandHandler.class);
    for (String name : names) {
      register(this.applicationContext, name);
    }
  }

  private void register(ApplicationContext applicationContext, String name) {
    Class<CommandHandler<?, ?>> handlerClass = (Class<CommandHandler<?, ?>>) applicationContext.getType(
        name);
    Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass,
        CommandHandler.class);
    if (generics == null) {
      logger.warn(String.format("CommandBus: Unable to get generic type of class %s", name));
      return;
    }
    Class<? extends Command> commandType = (Class<? extends Command>) generics[0];
    this.providerMap.put(commandType, new CommandHandlerProvider(applicationContext, handlerClass));
  }

}
