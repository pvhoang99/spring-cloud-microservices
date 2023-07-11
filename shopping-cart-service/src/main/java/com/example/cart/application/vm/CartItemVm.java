package com.example.cart.application.vm;

import com.example.cart.domain.cart.CartItem;
import lombok.Getter;
import org.springframework.beans.BeanUtils;

@Getter
public class CartItemVm {

  private Long productId;

  private Long price;

  private Long quantity;

  private String name;

  private String image;

  public static CartItemVm of(CartItem item) {
    CartItemVm cartItem = new CartItemVm();
    BeanUtils.copyProperties(item, cartItem);

    return cartItem;
  }

}
