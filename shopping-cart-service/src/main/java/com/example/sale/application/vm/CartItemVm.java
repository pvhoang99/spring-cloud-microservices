package com.example.sale.application.vm;

import lombok.Getter;

import java.util.Set;

@Getter
public class CartItemVm {

    private Long price;

    private Set<CartVm> items;

}
