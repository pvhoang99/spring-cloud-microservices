package com.example.cart.application.vm;

import com.example.cart.domain.cart.Cart;
import com.example.cart.domain.cart.Status;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class CartVm {

    private Long id;

    private Status status;

    private Long totalPrice;

    private Set<CartItemVm> items;

    public static CartVm of(Cart cart) {
        CartVm cartVm = new CartVm();
        cartVm.setId(cart.getId());
        cartVm.setStatus(cart.getStatus());
        cartVm.setTotalPrice(cart.getTotalPrice());
        cartVm.setItems(cart.getItems().values().stream().map(CartItemVm::of).collect(Collectors.toSet()));

        return cartVm;
    }
}
