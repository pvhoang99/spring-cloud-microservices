package com.example.sale.domain.cart.event;

import com.example.common.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartDeniedEvent implements DomainEvent {

    private Long cartId;

}
