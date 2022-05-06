package com.example.orderservice.config.statemachine;

import com.example.orderservice.dao.entity.Order.OrderEvents;
import com.example.orderservice.dao.entity.Order.OrderStatus;
import com.example.orderservice.saga.SagaService;
import java.util.EnumSet;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.data.mongodb.MongoDbPersistingStateMachineInterceptor;

@EnableStateMachineFactory
public class StateMachineConfig
    extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderEvents> {

  @Setter(onMethod_ = {@Autowired, @Lazy})
  private SagaService sagaService;

  @Setter(onMethod_ = {@Autowired})
  private MongoDbPersistingStateMachineInterceptor<OrderStatus, OrderEvents, String> persistingStateMachineInterceptor;

  @Override
  public void configure(StateMachineConfigurationConfigurer<OrderStatus, OrderEvents> config)
      throws Exception {
    config
        .withConfiguration()
        .autoStartup(false)
        .and()
        .withPersistence()
        .runtimePersister(persistingStateMachineInterceptor);
  }

  @Override
  public void configure(StateMachineStateConfigurer<OrderStatus, OrderEvents> states)
      throws Exception {
    states
        .withStates()
        .initial(OrderStatus.NEW)
        .states(EnumSet.allOf(OrderStatus.class));
  }

  @Override
  public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderEvents> transitions)
      throws Exception {
    transitions.withExternal()
        .source(OrderStatus.NEW)
        .target(OrderStatus.PENDING)
        .event(OrderEvents.CREAT)
        .action(sagaService::createInvoice)
    ;
  }
}
