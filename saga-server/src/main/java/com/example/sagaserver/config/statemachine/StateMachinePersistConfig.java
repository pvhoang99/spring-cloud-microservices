package com.example.sagaserver.config.statemachine;

import com.example.sagaserver.statemachine.event.Events;
import com.example.sagaserver.statemachine.state.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.data.jpa.JpaPersistingStateMachineInterceptor;
import org.springframework.statemachine.data.jpa.JpaStateMachineRepository;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;

@Configuration
public class StateMachinePersistConfig {

  @Bean
  public JpaPersistingStateMachineInterceptor<States, Events, String> stateMachineRuntimePersist(
      JpaStateMachineRepository jpaStateMachineRepository) {
    return new JpaPersistingStateMachineInterceptor<>(jpaStateMachineRepository);
  }

  @Bean
  public StateMachineService<States, Events> stateMachineService(
      final StateMachineFactory<States, Events> stateMachineFactory,
      final StateMachineRuntimePersister<States, Events, String> stateMachineRuntimePersist) {
    return new DefaultStateMachineService<>(stateMachineFactory, stateMachineRuntimePersist);
  }

}
