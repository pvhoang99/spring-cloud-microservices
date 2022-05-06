package com.example.orderservice.api.v1;

import com.example.orderservice.dao.entity.Order;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/order")
@RequiredArgsConstructor
public class OrderControllerV1 {

  private final OrderServiceV1 orderService;

  @RequestMapping(path = "/", method = RequestMethod.POST)
  public ResponseEntity<?> createOrder(@RequestBody Order order) throws Exception {
    assert  order != null;
    assert order.getCartItems() != null;
    assert order.getCartItems().size() > 0;
    return Optional.ofNullable(orderService.createOrder(order))
        .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
        .orElseThrow(() -> new Exception("Could not create the order"));
  }
}
