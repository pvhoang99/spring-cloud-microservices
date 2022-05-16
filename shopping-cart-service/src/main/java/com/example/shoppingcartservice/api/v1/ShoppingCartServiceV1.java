package com.example.shoppingcartservice.api.v1;

import com.example.common.utils.UserContextHolder;
import com.example.common.utils.UserContextHolder.UserContext;
import com.example.shoppingcartservice.client.AuthServiceFeignClient;
import com.example.shoppingcartservice.client.CatalogServiceFeignClient;
import com.example.shoppingcartservice.dao.entity.CartEvent;
import com.example.shoppingcartservice.dao.repository.CartEventRepository;
import com.example.shoppingcartservice.dto.Catalog;
import com.example.shoppingcartservice.dto.CheckoutResult;
import com.example.shoppingcartservice.dto.Product;
import com.example.shoppingcartservice.dto.ShoppingCart;
import com.example.shoppingcartservice.dto.User;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartServiceV1 {

  private final AuthServiceFeignClient authServiceFeignClient;
  private final CatalogServiceFeignClient catalogServiceFeignClient;
  private final CartEventRepository cartEventRepository;

  public Boolean addCartEvent(CartEvent cartEvent) {
    UserContext context = UserContextHolder.getContext();
    if (context != null) {
      cartEvent.setUserId(context.getUserId());
      cartEventRepository.save(cartEvent);
    } else {
      return null;
    }
    return true;
  }

  public CheckoutResult checkout() {
    CheckoutResult checkoutResult = new CheckoutResult();

    ShoppingCart currentCart = null;
    try {
      currentCart = getShoppingCart();
    } catch (Exception e) {
      log.error("Could not retrieve shopping cart", e);
    }

    if (Objects.nonNull(currentCart)) {
      List<Product> products = catalogServiceFeignClient.getProductByProductIds(
          currentCart.getCartItems()
              .stream()
              .map(e -> e.getProduct().getProductId())
              .collect(Collectors.joining(",")));

      if (checkAvailableProduct(currentCart, products)) {

      }
    }

    return checkoutResult;
  }

  private boolean checkAvailableProduct(ShoppingCart currentCart, List<Product> products) {
    //TODO: must retrieval
//    return currentCart.getCartItems().stream().anyMatch(e -> {
//      Product product = products.stream()
//          .filter(k -> Objects.equals(k.getProductId(), e.getProduct().getProductId())).findFirst()
//          .orElseThrow(() -> new RuntimeException("product in cart not exist in catalog"));
//      return product.getQuantity() >= e.getQuantity();
//    });

    return true;
  }


  public ShoppingCart getShoppingCart() throws Exception {
    User user = authServiceFeignClient.getCurrentUser();
    ShoppingCart shoppingCart = null;
    if (user != null) {
      Catalog catalog = catalogServiceFeignClient.catalog(1L);
      shoppingCart = aggregateCartEvents(user, catalog);
    }
    return shoppingCart;
  }

  private ShoppingCart aggregateCartEvents(User user, Catalog catalog) throws Exception {
    Flux<CartEvent> cartEvents = Flux.fromIterable(
        cartEventRepository.getCartEventStreamByUserId(user.getUserId()));

    ShoppingCart shoppingCart = cartEvents.takeWhile(
            cartEvent -> !ShoppingCart.isTerminal(cartEvent.getCartEventType()))
        .reduceWith(() -> new ShoppingCart(catalog), ShoppingCart::incorporate).block();

    assert shoppingCart != null;
    shoppingCart.getCartItems();
    return shoppingCart;
  }
}
