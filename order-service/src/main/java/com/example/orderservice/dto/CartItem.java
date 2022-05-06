package com.example.orderservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItem {

  private Product product;
  private Integer quantity;

  public CartItem(Product product, Integer quantity) {
    this.product = product;
    this.quantity = quantity;
  }
}
