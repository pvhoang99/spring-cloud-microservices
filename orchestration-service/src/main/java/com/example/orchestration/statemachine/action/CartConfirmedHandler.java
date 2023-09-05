package com.example.orchestration.statemachine.action;

import com.example.orchestration.statemachine.SagaEvent;
import com.example.orchestration.statemachine.SagaState;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor(staticName = "init")
public class CartConfirmedHandler implements Action<SagaState, SagaEvent> {

    @Override
    public void execute(StateContext<SagaState, SagaEvent> context) {
        log.info("CartConfirmed with context {}", context);
    }

}
