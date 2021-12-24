package com.example.orderservice.config;

import com.example.orderservice.state.OrderStatus;
import com.example.orderservice.state.OrderStatusChangeEvent;
import java.util.EnumSet;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends
    StateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvent> {

  @Override
  public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvent> states)
      throws Exception {
    states
        .withStates()
        .initial(OrderStatus.WAIT_PAYMENT)
        .states(EnumSet.allOf(OrderStatus.class));
  }

  @Override
  public void configure(
      StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvent> transitions)
      throws Exception {
    transitions
        .withExternal()
        .source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.WAIT_DELIVER)
        .event(OrderStatusChangeEvent.PAYED)
        .and()
        .withExternal()
        .source(OrderStatus.WAIT_DELIVER).target(OrderStatus.WAIT_RECEIVE)
        .event(OrderStatusChangeEvent.DELIVERY)
        .and()
        .withExternal()
        .source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH)
        .event(OrderStatusChangeEvent.RECEIVED)
        .and()
        .withExternal()
        .source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.CLOSED)
        .event(OrderStatusChangeEvent.REFUND);
  }

}
