package com.example.orderservice.config.statemachine;

import com.example.orderservice.dao.entity.Order.OrderEvents;
import com.example.orderservice.dao.entity.Order.OrderStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.data.mongodb.MongoDbPersistingStateMachineInterceptor;
import org.springframework.statemachine.data.mongodb.MongoDbStateMachineRepository;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;

@Configuration
public class StateMachinePersistConfig {

  @Bean
  public StateMachineRuntimePersister<OrderStatus, OrderEvents, String> stateMachineRuntimePersister(
      MongoDbStateMachineRepository mongoDbStateMachineRepository) {
    return new MongoDbPersistingStateMachineInterceptor<>(
        mongoDbStateMachineRepository);
  }

  @Bean
  public StateMachineService<OrderStatus, OrderEvents> stateMachineService(
      final StateMachineFactory<OrderStatus, OrderEvents> stateMachineFactory,
      final StateMachineRuntimePersister<OrderStatus, OrderEvents, String> stateMachineRuntimePersist) {
    return new DefaultStateMachineService<>(stateMachineFactory, stateMachineRuntimePersist);
  }

}
