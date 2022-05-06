package com.example.orderservice.saga;

import com.example.orderservice.dao.entity.Order;
import com.example.orderservice.dao.entity.Order.OrderEvents;
import com.example.orderservice.dao.entity.Order.OrderStatus;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Service;

@Service
public class SagaService {

  @Setter(onMethod_ = {@Autowired})
  private StateMachineService<OrderStatus, OrderEvents> stateMachineService;

  private StateMachine<OrderStatus, OrderEvents> currentStateMachine;


  @SuppressWarnings("deprecation")
  public void signalStateMachine(Message<OrderEvents> message) {
    currentStateMachine.sendEvent(message);
  }

  @Async("threadPoolTaskExecutor")
  public void sendEventCreatNewOrder(Order order) {
    Message<OrderEvents> message = MessageBuilder.withPayload(OrderEvents.CREAT)
        .setHeader("orderId", order.getOrderId()).build();
    currentStateMachine = stateMachineService.acquireStateMachine(order.getOrderId().toHexString(),
        true);
    this.signalStateMachine(message);
  }


  public void createInvoice(StateContext<OrderStatus, OrderEvents> context) {
    System.out.println("a");
    Message<OrderEvents> message = context.getMessage();

  }
}
