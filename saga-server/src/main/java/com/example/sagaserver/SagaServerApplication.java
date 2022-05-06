package com.example.sagaserver;

import com.example.sagaserver.statemachine.event.Events;
import com.example.sagaserver.statemachine.state.States;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineEventResult;
import org.springframework.statemachine.service.StateMachineService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class SagaServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SagaServerApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(
      StateMachineService<States, Events> statesEventsStateMachineService) {
    return args -> {

      StateMachine<States, Events> machineService = statesEventsStateMachineService.acquireStateMachine(
          "id2");
      Message<Events> message = MessageBuilder.withPayload(Events.E1)
          .build();
      Flux<StateMachineEventResult<States, Events>> resultFlux = machineService.sendEvent(
          Mono.just(message));
    };
  }

}
