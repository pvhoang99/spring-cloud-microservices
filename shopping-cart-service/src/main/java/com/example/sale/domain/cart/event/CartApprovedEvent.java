package com.example.sale.domain.cart.event;

import com.example.common.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class CartApprovedEvent implements DomainEvent  {

    private Long cartId;

}
