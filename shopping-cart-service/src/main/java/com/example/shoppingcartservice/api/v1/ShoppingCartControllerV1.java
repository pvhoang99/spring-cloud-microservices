package com.example.shoppingcartservice.api.v1;


import com.example.shoppingcartservice.dao.entity.CartEvent;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class ShoppingCartControllerV1 {

  private final ShoppingCartServiceV1 shoppingCartService;

  @RequestMapping(path = "/events", method = RequestMethod.POST)
  public ResponseEntity<?> addCartEvent(@RequestBody CartEvent cartEvent) {
    return Optional.ofNullable(shoppingCartService.addCartEvent(cartEvent))
        .map(event -> new ResponseEntity<>(HttpStatus.NO_CONTENT))
        .orElseThrow(() -> new RuntimeException("Could not find shopping cart"));
  }

  @RequestMapping(path = "/checkout", method = RequestMethod.POST)
  public ResponseEntity<?> checkoutCart() {
    return Optional.ofNullable(shoppingCartService.checkout())
        .map(checkoutResult -> new ResponseEntity<>(checkoutResult, HttpStatus.OK))
        .orElseThrow(() -> new RuntimeException("Could not checkout"));
  }

  @RequestMapping(path = "/cart", method = RequestMethod.GET)
  public ResponseEntity<?> getCart() throws Exception {
    return Optional.ofNullable(shoppingCartService.getShoppingCart())
        .map(cart -> new ResponseEntity<>(cart, HttpStatus.OK))
        .orElseThrow(() -> new Exception("Could not find shopping cart"));
  }
}
