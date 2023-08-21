package com.example.order.application.api.v1;

import com.example.order.domain.order.Order;
import com.example.order.domain.order.Order.OrderStatus;
import com.example.order.domain.order.OrderRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderServiceV1 {

  @Setter(onMethod_ = {@Autowired})
  private OrderRepository orderRepository;
  @Setter(onMethod_ = {@Autowired})
  private SagaService sagaService;

  public Order createOrder(Order order) {
    order.setOrderStatus(OrderStatus.NEW);

    order = orderRepository.save(order);
    final Order finalOrder = order;
    CompletableFuture.runAsync(() -> sagaService.sendEventCreatNewOrder(finalOrder));
    return order;
  }
}
