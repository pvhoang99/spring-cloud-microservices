//package com.example.orderservice.service;
//
//import com.example.common.dto.OrderRequestDto;
//import com.example.common.event.OrderEvent;
//import com.example.common.event.OrderStatus;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Sinks;
//
//@Service
//@RequiredArgsConstructor
//public class OrderStatusPublisher {
//
//  private final Sinks.Many<OrderEvent> orderSinks;
//
//  public void publishOrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus){
//    OrderEvent orderEvent=new OrderEvent(orderRequestDto,orderStatus);
//    orderSinks.tryEmitNext(orderEvent);
//  }
//}
