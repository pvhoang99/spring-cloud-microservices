package com.example.cart.infrastructure.listener.event;

import com.example.cart.domain.cart.event.CartConfirmedEvent;
import com.example.cart.infrastructure.client.OrchestrationFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class DomainEventListener {

    private final OrchestrationFeignClient orchestrationFeignClient;

    @TransactionalEventListener(classes = CartConfirmedEvent.class)
    public void cartConfirmedHandler(CartConfirmedEvent event) {
        log.info("cartConfirmedHandler with event {}", event);
        String transactionId = event.getCart().getTransactionId();
        this.orchestrationFeignClient.sendEvent(transactionId, "CART_CONFIRMED");
    }
}
