package com.example.orchestration.handler;

import com.example.common.utils.MessageUtils;
import com.example.orchestration.statemachine.SagaEvent;
import com.example.orchestration.statemachine.SagaState;
import com.example.orchestration.statemachine.StateMachineStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/transaction")
@RequiredArgsConstructor
public class CartEventHandleController {
    private final StateMachineStorage stateMachineStorage;

    @PostMapping("/{transactionId}/{event}")
    public ResponseEntity<Void> handle(@PathVariable String transactionId, @PathVariable SagaEvent event) {
        StateMachine<SagaState, SagaEvent> stateMachine = this.stateMachineStorage.acquireStateMachine(transactionId);
        Map<String, Object> headers = Map.of(
                "transactionId", transactionId
        );

        stateMachine.sendEvent(
                MessageUtils.from(event, headers)
        ).blockLast();

        var currentState = stateMachine.getState();
        log.info("current state: {}", currentState);

        return ResponseEntity.ok().build();
    }
}
