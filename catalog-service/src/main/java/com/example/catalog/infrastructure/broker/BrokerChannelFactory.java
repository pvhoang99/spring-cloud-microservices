//package com.example.catalog.infrastructure.broker;
//
//import com.example.catalog.domain.product.ProductCreatedEvent;
//import com.example.common.event.DomainEvent;
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class BrokerChannelFactory {
//
//    private final Processor processor;
//
//    public MessageChannel of(DomainEvent event) {
//        if (event instanceof ProductCreatedEvent) {
//            return this.processor.productCreated();
//        }
//
//        return null;
//    }
//
//}
