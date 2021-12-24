package com.example.orderservice.config;

import com.example.orderservice.service.OrderStateService;
import com.example.orderservice.state.OrderStatus;
import com.example.orderservice.state.OrderStatusChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

/**
 * Created by lijingyao on 2017/11/24 10:40.
 */
@Configuration
public class OrderPersistHandlerConfig {

    @Autowired
    private StateMachine<OrderStatus, OrderStatusChangeEvent> stateMachine;


    @Bean
    public OrderStateService persist() {
        PersistStateMachineHandler handler = persistStateMachineHandler();
        handler.addPersistStateChangeListener(persistStateChangeListener());
        return new OrderStateService(handler);
    }

    @Bean
    public PersistStateMachineHandler persistStateMachineHandler() {
        return new PersistStateMachineHandler(stateMachine);
    }

    @Bean
    public OrderPersistStateChangeListener persistStateChangeListener(){
        return new OrderPersistStateChangeListener();
    }
}
