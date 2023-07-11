package com.example.cart.application.vm;

import com.example.cart.domain.cart.Cart;
import com.example.cart.domain.cart.Status;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

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
    cartVm.setItems(cart.getItems().values().stream().map(CartItemVm::of).collect(Collectors.toSet()));

    return cartVm;
  }
}
