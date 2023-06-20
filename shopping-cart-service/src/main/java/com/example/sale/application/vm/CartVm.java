package com.example.sale.application.vm;

import lombok.Getter;

import java.util.Set;

@Getter
public class CartVm {

    private Long totalPrice;

    private Set<CartItemVm> items;

}
