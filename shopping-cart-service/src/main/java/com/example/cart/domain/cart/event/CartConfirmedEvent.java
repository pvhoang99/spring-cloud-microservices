package com.example.cart.domain.cart.event;

import com.example.common.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class CartConfirmedEvent implements DomainEvent {
    private Long id;
}
