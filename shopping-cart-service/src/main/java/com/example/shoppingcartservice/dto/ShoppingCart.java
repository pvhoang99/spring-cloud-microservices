package com.example.shoppingcartservice.dto;

import com.example.shoppingcartservice.dao.entity.CartEvent;
import com.example.shoppingcartservice.dao.entity.CartEvent.CartEventType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Flux;

@Getter
@Setter
@NoArgsConstructor
public class ShoppingCart {

  private Map<String, Integer> productMap = new HashMap<>();
  private List<CartItem> cartItems = new ArrayList<>();
  private Catalog catalog;

  public ShoppingCart(Catalog catalog) {
    this.catalog = catalog;
  }

  public ShoppingCart incorporate(CartEvent cartEvent) {
    Flux<CartEventType> validCartEventTypes =
        Flux.fromStream(Stream.of(CartEventType.ADD_ITEM, CartEventType.REMOVE_ITEM));

    if (validCartEventTypes.toStream().anyMatch(cartEventType ->
        cartEvent.getCartEventType().equals(cartEventType))) {
      productMap.put(cartEvent.getProductId(),
          productMap.getOrDefault(cartEvent.getProductId(), 0) +
              (cartEvent.getQuantity() * (cartEvent.getCartEventType()
                  .equals(CartEventType.ADD_ITEM) ? 1 : -1))
      );
    }
    return this;
  }

  public static Boolean isTerminal(CartEventType cartEventType) {
    return (cartEventType == CartEventType.CLEAR_CART || cartEventType == CartEventType.CHECKOUT);
  }

  public List<CartItem> getCartItems() {
    cartItems = productMap.entrySet().stream()
        .map(item -> new CartItem(catalog.getProducts().stream()
            .filter(product -> Objects.equals(product.getProductId(), item.getKey()))
            .findFirst()
            .orElse(null), item.getValue()))
        .filter(item -> item.getQuantity() > 0)
        .collect(Collectors.toList());

    if (cartItems.stream().anyMatch(item -> item.getProduct() == null)) {
      throw new RuntimeException("cartItems is empty");
    }
    return cartItems;
  }

}
