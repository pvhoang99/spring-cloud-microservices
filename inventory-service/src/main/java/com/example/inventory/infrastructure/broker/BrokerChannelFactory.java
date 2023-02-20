package com.example.inventory.infrastructure.broker;

import com.example.common.event.DomainEvent;
import com.example.inventory.domain.product.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrokerChannelFactory {

    private final Processor processor;

    public MessageChannel of(DomainEvent event) {
        if (event instanceof ProductCreatedEvent) {
            return this.processor.productCreated();
        }

        return null;
    }

}
