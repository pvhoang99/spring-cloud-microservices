package com.example.shoppingcartservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckoutResult {

  private String resultMessage;
  private Order order;

}
