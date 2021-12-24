package com.example.shoppingcartservice.cart;


import com.example.shoppingcartservice.product.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.axonframework.modelling.command.EntityId;

@Getter
@Setter
@ToString
public class CartItem {

  @EntityId
  private String transactionId;
  private String productId;
  private Product product;
  private Integer quantity;
}
