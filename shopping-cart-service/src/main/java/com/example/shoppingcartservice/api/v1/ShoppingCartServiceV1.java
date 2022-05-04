package com.example.shoppingcartservice.api.v1;

import com.example.shoppingcartservice.client.AuthServiceFeignClient;
import com.example.shoppingcartservice.client.InventoryServiceFeignClient;
import com.example.shoppingcartservice.dao.entity.CartEvent;
import com.example.shoppingcartservice.dao.repository.CartEventRepository;
import com.example.shoppingcartservice.dto.Catalog;
import com.example.shoppingcartservice.dto.CheckoutResult;
import com.example.shoppingcartservice.dto.ShoppingCart;
import com.example.shoppingcartservice.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceV1 {

  private final AuthServiceFeignClient authServiceFeignClient;
  private final InventoryServiceFeignClient inventoryServiceFeignClient;
  private final CartEventRepository cartEventRepository;

  public Boolean addCartEvent(CartEvent cartEvent) {
    User user = authServiceFeignClient.getCurrentUser();
    if (user != null) {
      cartEvent.setUserId(user.getUserId());
      cartEventRepository.save(cartEvent);
    } else {
      return null;
    }
    return true;
  }

  public CheckoutResult checkout() {

    return null;
  }

  public ShoppingCart getShoppingCart() throws Exception {
    User user = authServiceFeignClient.getCurrentUser();
    ShoppingCart shoppingCart = null;
    if (user != null) {
      Catalog catalog = inventoryServiceFeignClient.getCatalog();
      shoppingCart = aggregateCartEvents(user, catalog);
    }
    return shoppingCart;
  }

  private ShoppingCart aggregateCartEvents(User user, Catalog catalog) throws Exception {
    Flux<CartEvent> cartEvents = Flux.fromStream(
        cartEventRepository.getCartEventStreamByUserId(user.getUserId()));

    ShoppingCart shoppingCart = cartEvents.takeWhile(
            cartEvent -> !ShoppingCart.isTerminal(cartEvent.getCartEventType()))
        .reduceWith(() -> new ShoppingCart(catalog), ShoppingCart::incorporate).block();

    assert shoppingCart != null;
    shoppingCart.getCartItems();
    return shoppingCart;
  }
}
