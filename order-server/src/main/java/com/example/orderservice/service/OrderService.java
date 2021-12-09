//package com.example.orderservice.service;
//
//import com.example.common.dto.OrderRequestDto;
//import com.example.common.event.OrderStatus;
//import com.example.common.event.PaymentStatus;
//import com.example.orderservice.entity.PurchaseOrder;
//import com.example.orderservice.repository.OrderRepository;
//import java.util.List;
//import java.util.function.Consumer;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class OrderService {
//
//  private final OrderRepository orderRepository;
//
//  private final OrderStatusPublisher orderStatusPublisher;
//
//  @Transactional(rollbackFor = Exception.class)
//  public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {
//    PurchaseOrder order = orderRepository.save(convertDtoToEntity(orderRequestDto));
//    orderRequestDto.setOrderId(order.getId());
//    //produce kafka event with status ORDER_CREATED
//    orderStatusPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);
//    return order;
//  }
//
//  @Transactional
//  public void updateOrder(int id, Consumer<PurchaseOrder> consumer) {
//    orderRepository.findById(id).ifPresent(consumer.andThen(this::updateOrder));
//  }
//
//  private void updateOrder(PurchaseOrder purchaseOrder) {
//    boolean isPaymentComplete = PaymentStatus.PAYMENT_COMPLETED.equals(purchaseOrder.getPaymentStatus());
//    OrderStatus orderStatus = isPaymentComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
//    purchaseOrder.setOrderStatus(orderStatus);
//    if (!isPaymentComplete) {
//      orderStatusPublisher.publishOrderEvent(convertEntityToDto(purchaseOrder), orderStatus);
//    }
//  }
//
//
//  public List<PurchaseOrder> getAllOrders(){
//    return orderRepository.findAll();
//  }
//
//  private PurchaseOrder convertDtoToEntity(OrderRequestDto dto) {
//    PurchaseOrder purchaseOrder = new PurchaseOrder();
//    purchaseOrder.setProductId(dto.getProductId());
//    purchaseOrder.setUserId(dto.getUserId());
//    purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
//    purchaseOrder.setPrice(dto.getAmount());
//    return purchaseOrder;
//  }
//
//  public OrderRequestDto convertEntityToDto(PurchaseOrder purchaseOrder) {
//    OrderRequestDto orderRequestDto = new OrderRequestDto();
//    orderRequestDto.setOrderId(purchaseOrder.getId());
//    orderRequestDto.setUserId(purchaseOrder.getUserId());
//    orderRequestDto.setAmount(purchaseOrder.getPrice());
//    orderRequestDto.setProductId(purchaseOrder.getProductId());
//    return orderRequestDto;
//  }
//
//}
