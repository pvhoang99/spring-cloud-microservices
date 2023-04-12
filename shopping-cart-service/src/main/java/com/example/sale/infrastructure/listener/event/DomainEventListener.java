package com.example.sale.infrastructure.listener.event;

import com.example.common.command.CommandBus;
import com.example.sale.domain.cart.event.CartApprovedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class DomainEventListener {

    private final CommandBus commandBus;

    @TransactionalEventListener
    public void cartApproved(CartApprovedEvent event) {
        this.commandBus.execute(CreateOrderCommand.of(event.getCartId()));
        this.commandBus.execute(SendMailCommand.of(event.getCartId()));
    }

    @TransactionalEventListener
    public void cartDenied(CartApprovedEvent event) {
        this.commandBus.execute(SendMailCommand.of(event.getCartId()));
    }

}
