package com.example.auth.config.axon;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.common.transaction.NoTransactionManager;
import org.axonframework.messaging.unitofwork.RollbackConfigurationType;
import org.axonframework.queryhandling.DefaultQueryGateway;
import org.axonframework.queryhandling.QueryBus;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SimpleQueryBus;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MyAxonConfiguration {

  /**
   * Commands may be placed on a queue for asynchronous processing, rather than being processed synchronously.
   * Commands must manage by Transaction
   */
  @Bean
  public CommandBus commandBus(PlatformTransactionManager platformTransactionManager) {
    return SimpleCommandBus.builder()
        .transactionManager(new SpringTransactionManager(platformTransactionManager))
        .rollbackConfiguration(RollbackConfigurationType.ANY_THROWABLE)
        .build();
  }

  /**
   * Queries never modify the database. A query returns a DTO that does not encapsulate any domain knowledge.
   */
  @Bean
  public QueryBus queryBus() {
    return SimpleQueryBus.builder()
        .transactionManager(NoTransactionManager.INSTANCE)
        .build();
  }

  @Bean
  public CommandGateway commandGateway(CommandBus commandBus, ValidationCommandInterceptor interceptor) {
    return DefaultCommandGateway.builder()
        .commandBus(commandBus)
        .dispatchInterceptors(interceptor)
        .build();
  }

  @Bean
  public QueryGateway queryGateway(QueryBus queryBus) {
    return DefaultQueryGateway.builder()
        .queryBus(queryBus)
        .build();
  }

}
