package com.example.orchestration.service;

import com.example.orchestration.statemachine.SagaEvent;
import com.example.orchestration.statemachine.SagaState;
import com.example.orchestration.statemachine.StateMachineStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartEventHandleService {




    public void cartConfirmedHandle(String transactionId) {

    }


}
