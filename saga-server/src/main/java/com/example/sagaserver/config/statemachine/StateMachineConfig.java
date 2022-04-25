package com.example.sagaserver.config.statemachine;

import com.example.sagaserver.statemachine.event.Events;
import com.example.sagaserver.statemachine.state.States;
import java.util.EnumSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.action.Actions;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@EnableStateMachineFactory(contextEvents = false)
@Configuration
public class StateMachineConfig
    extends EnumStateMachineConfigurerAdapter<States, Events> {

  @Override
  public void configure(StateMachineConfigurationConfigurer<States, Events> config)
      throws Exception {
    config
        .withConfiguration()
        .autoStartup(true)
        .listener(listener());
  }

  @Override
  public void configure(StateMachineStateConfigurer<States, Events> states)
      throws Exception {
    states
        .withStates()
        .initial(States.SI)
        .state(States.SI, actionEntry(), actionExit())
        .states(EnumSet.allOf(States.class));
  }

  @Override
  public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
      throws Exception {
    transitions
        .withExternal()
        .source(States.SI).target(States.S1).event(Events.E1)
        .guard(guard())
        .action(Actions.errorCallingAction(action(), errAction()))
        .and()
        .withExternal()
        .source(States.S1).target(States.S2).event(Events.E2).action(action());

  }

  @Bean
  public StateMachineListener<States, Events> listener() {
    return new StateMachineListenerAdapter<States, Events>() {
      @Override
      public void stateChanged(State<States, Events> from, State<States, Events> to) {
        System.out.println("State change to " + to.getId());
      }
    };
  }

  @Bean
  public Action<States, Events> action() {
    return context -> {
      System.out.println("action between");
    };
  }

  @Bean
  public Action<States, Events> actionEntry() {
    return new Action<States, Events>() {
      @Override
      public void execute(StateContext<States, Events> context) {
        System.out.println("action entry");
      }
    };
  }

  @Bean
  public Action<States, Events> actionExit() {
    return new Action<States, Events>() {
      @Override
      public void execute(StateContext<States, Events> context) {
        System.out.println("action exit");
      }
    };
  }

  public Guard<States, Events> guard() {
    return new Guard<States, Events>() {
      @Override
      public boolean evaluate(StateContext<States, Events> context) {
        return true;
      }
    };
  }

  @Bean
  public Action<States, Events> errAction() {
    return context -> {
      System.out.println("error action");
    };
  }

}
