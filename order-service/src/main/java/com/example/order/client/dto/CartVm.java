package com.example.order.client.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
public class CartVm {

    private Long id;

    private Long totalPrice;

    private Set<CartItemVm> items;

}
