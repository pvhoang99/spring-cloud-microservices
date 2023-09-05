package com.example.orchestration.statemachine;

import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StateMachineStorage {

    private static final Map<String, StateMachine<SagaState, SagaEvent>> stateMachineInMemory = new HashMap<>();

    private final StateMachineFactory<SagaState, SagaEvent> stateMachineFactory;

    public StateMachine<SagaState, SagaEvent> acquireStateMachine(String transactionId) {
        StateMachine<SagaState, SagaEvent> stateMachine = stateMachineInMemory.get(transactionId);
        if (stateMachine == null) {
            stateMachine = this.stateMachineFactory.getStateMachine(transactionId);
            stateMachineInMemory.put(transactionId, stateMachine);
        }

        return stateMachine;
    }
}
