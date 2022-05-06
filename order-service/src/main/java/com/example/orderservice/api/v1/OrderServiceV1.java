package com.example.orderservice.api.v1;

import com.example.orderservice.dao.entity.Order;
import com.example.orderservice.dao.entity.Order.OrderStatus;
import com.example.orderservice.dao.repository.OrderRepository;
import com.example.orderservice.saga.SagaService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV1 {

  @Setter(onMethod_ = {@Autowired})
  private OrderRepository orderRepository;
  @Setter(onMethod_ = {@Autowired})
  private SagaService sagaService;

  public Order createOrder(Order order) {
    order.setOrderStatus(OrderStatus.NEW);

    order = orderRepository.save(order);
    sagaService.sendEventCreatNewOrder(order);

    return order;
  }


}
