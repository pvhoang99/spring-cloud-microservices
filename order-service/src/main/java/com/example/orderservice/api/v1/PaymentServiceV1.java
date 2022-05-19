package com.example.orderservice.api.v1;

import com.example.orderservice.saga.SagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceV1 {

  private SagaService sagaService;

  public void event() {

  }

}
