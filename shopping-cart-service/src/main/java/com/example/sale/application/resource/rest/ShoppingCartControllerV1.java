package com.example.sale.application.resource.rest;

import com.example.common.command.CommandBus;
import com.example.sale.application.command.cart.AddCartItemCommand;
import com.example.sale.application.command.cart.CreateCartCommand;
import com.example.sale.application.command.cart.EditCartItemCommand;
import com.example.sale.application.command.cart.RemoveCartItemCommand;
import com.example.sale.domain.cart.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class ShoppingCartControllerV1 {

    private final CommandBus commandBus;

    @PostMapping("/carts")
    public ResponseEntity<Void> createCart() {
        this.commandBus.execute(CreateCartCommand.of());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/carts/{cartId}/add-item")
    public ResponseEntity<Void> addItemToCart(@PathVariable Long cartId, @RequestBody CartItem cartItem) {
        this.commandBus.execute(AddCartItemCommand.of(cartId, cartItem));

        return ResponseEntity.ok().build();
    }

    @PutMapping("/carts/{cartId}/items/{itemId}")
    public ResponseEntity<Void> editCartItem(@PathVariable Long cartId, @PathVariable(value = "itemId") Long cartItemId, @RequestBody EditCartItemCommand command) {
        command.setCartItemId(cartId);
        command.setCartItemId(cartItemId);
        this.commandBus.execute(command);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/carts/{cartId}/items/{itemId}/remove-item")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        this.commandBus.execute(RemoveCartItemCommand.of(cartId, itemId));

        return ResponseEntity.ok().build();
    }

}
