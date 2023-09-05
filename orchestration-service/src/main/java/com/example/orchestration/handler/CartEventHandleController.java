package com.example.orchestration.handler;

import com.example.orchestration.statemachine.SagaEvent;
import com.example.orchestration.statemachine.SagaState;
import com.example.orchestration.statemachine.StateMachineStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cart-event")
@RequiredArgsConstructor
public class CartEventHandleController {
    private final StateMachineStorage stateMachineStorage;

    @PostMapping("/{transactionId}/confirmed")
    public ResponseEntity<Void> confirmedHandle(@PathVariable String transactionId) {
        StateMachine<SagaState, SagaEvent> stateMachine = this.stateMachineStorage.acquireStateMachine(transactionId);

        stateMachine.sendEvent(MessageBuilder.withPayload(SagaEvent.CART_CONFIRMED).build());

        return ResponseEntity.ok().build();
    }
}
