package com.example.shoppingcartservice.aggregate;

import com.example.shoppingcartservice.cart.CartItem;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ShoppingCart {

  private Logger logger = Logger.getLogger(this.getClass().getName());

  @AggregateIdentifier
  private String id;

  @AggregateMember
  private List<CartItem> cartItems = new ArrayList<>();





}
