package com.example.orchestration.statemachine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

import static com.example.orchestration.statemachine.SagaEvent.CART_CONFIRMED;
import static com.example.orchestration.statemachine.SagaState.ORDER_CREATED;
import static com.example.orchestration.statemachine.SagaState.WAIT_FOR_NEW_ORDER;

@Slf4j
@Configuration
@EnableStateMachineFactory
@RequiredArgsConstructor
public class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<SagaState, SagaEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<SagaState, SagaEvent> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(this.listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<SagaState, SagaEvent> states)
            throws Exception {
        states
                .withStates()
                .initial(WAIT_FOR_NEW_ORDER)
                .states(EnumSet.allOf(SagaState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<SagaState, SagaEvent> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(WAIT_FOR_NEW_ORDER).target(ORDER_CREATED)
                .action(this.cartConfirmed(), this.cartConfirmedError())
                .event(CART_CONFIRMED)

        ;
    }

    @Bean
    public StateMachineListener<SagaState, SagaEvent> listener() {
        return new StateMachineListenerAdapter<>() {
            @Override
            public void stateChanged(State<SagaState, SagaEvent> from, State<SagaState, SagaEvent> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }

    @Bean
    public Action<SagaState, SagaEvent> cartConfirmed() {
        return
                context -> {
                    log.info("CartConfirmed with context {}", context);
                };
    }


    @Bean
    public Action<SagaState, SagaEvent> cartConfirmedError() {
        return context -> log.error("CartConfirmedError error with context {}", context);
    }

}
