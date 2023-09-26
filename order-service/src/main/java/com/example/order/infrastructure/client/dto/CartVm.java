package com.example.order.infrastructure.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
public class CartVm {

    private Long id;

    private Long totalPrice;

    @Setter
    private String transactionId;

    private Set<CartItemVm> items;

}
