package com.example.order.application.api.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/payment")
@RequiredArgsConstructor
public class PaymentControllerV1 {

  private final PaymentServiceV1 paymentService;

  @PostMapping("/event")
  public ResponseEntity<?> paymentEvent() {
    paymentService.event();
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
