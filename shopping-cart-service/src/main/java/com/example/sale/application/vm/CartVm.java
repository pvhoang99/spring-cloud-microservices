package com.example.sale.application.vm;

import com.example.sale.domain.cart.Cart;
import com.example.sale.domain.cart.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class CartVm {

    private Status status;

    private Long totalPrice;

    private Set<CartItemVm> items;

    public static CartVm of(Cart cart) {
        CartVm cartVm = new CartVm();
        cartVm.status = cart.getStatus();
        cartVm.totalPrice = cart.getTotalPrice();
        cartVm.setItems(cart.getItems().stream().map(CartItemVm::of).collect(Collectors.toSet()));

        return cartVm;
    }
}
