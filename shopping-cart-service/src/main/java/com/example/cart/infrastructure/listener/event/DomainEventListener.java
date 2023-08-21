package com.example.cart.infrastructure.listener.event;

import com.example.cart.domain.cart.event.CartConfirmedEvent;
import com.example.cart.infrastructure.client.OrderFeignClient;
import com.example.common.command.CommandBus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class DomainEventListener {

    private final CommandBus commandBus;

    @TransactionalEventListener(classes = CartConfirmedEvent.class)
    public void cartConfirmedHandler(CartConfirmedEvent event) {
    }
}
