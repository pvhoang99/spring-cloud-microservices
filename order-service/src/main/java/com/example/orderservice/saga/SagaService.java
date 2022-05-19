package com.example.orderservice.saga;

import com.example.orderservice.dao.entity.Invoice;
import com.example.orderservice.dao.entity.Invoice.InvoiceStatus;
import com.example.orderservice.dao.entity.Order;
import com.example.orderservice.dao.entity.Order.OrderEvents;
import com.example.orderservice.dao.entity.Order.OrderStatus;
import com.example.orderservice.dao.repository.InvoiceRepository;
import com.example.orderservice.dao.repository.OrderRepository;
import com.example.orderservice.dto.CartItem;
import java.util.List;
import lombok.Setter;
import org.bson.types.ObjectId;
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

  @Setter(onMethod_ = {@Autowired})
  private OrderRepository orderRepository;

  @Setter(onMethod_ = {@Autowired})
  private InvoiceRepository invoiceRepository;

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

  public void sendEventPayment() {

  }

  public void subtractProduct(StateContext<OrderStatus, OrderEvents> context) {

  }

  public void createInvoice(StateContext<OrderStatus, OrderEvents> context) {
    Message<OrderEvents> message = context.getMessage();
    String orderId = message.getHeaders().get("orderId", String.class);
    assert orderId != null;
    ObjectId objectId = new ObjectId(orderId);
    Order order = orderRepository.findById(objectId)
        .orElseThrow(() -> new RuntimeException("order not exist with: " + orderId));

    List<CartItem> cartItems = order.getCartItems();
    double totalMoney = 0;
    for (CartItem cartItem : cartItems) {
      totalMoney = totalMoney + cartItem.getProduct().getUnitPrice() * cartItem.getQuantity();
    }

    Invoice invoice = new Invoice(10, InvoiceStatus.CREATED);
    invoiceRepository.save(invoice);
  }
}
