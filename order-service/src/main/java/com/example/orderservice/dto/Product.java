package com.example.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

  private String name, productId, description;
  private Double unitPrice;
  private int quantity;

}
