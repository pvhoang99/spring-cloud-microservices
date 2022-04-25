package com.example.sagaserver;

import com.example.sagaserver.statemachine.event.Events;
import com.example.sagaserver.statemachine.state.States;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.service.StateMachineService;

@SpringBootApplication
public class SagaServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SagaServerApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(
      StateMachineService<States,Events> statesEventsStateMachineService) {
    return args -> {

    statesEventsStateMachineService.acquireStateMachine("id1");
    };
  }

}
