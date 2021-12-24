package com.example.shoppingcartservice.cart;

import com.example.shoppingcartservice.catalog.Catalog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ShoppingCart {

  private Logger logger = Logger.getLogger(this.getClass().getName());
  private Map<String, Integer> productMap = new HashMap<>();
  private List<CartItem> cartItems = new ArrayList<>();
  private Catalog catalog;

  public ShoppingCart(Catalog catalog) {
    this.catalog = catalog;
  }

}
