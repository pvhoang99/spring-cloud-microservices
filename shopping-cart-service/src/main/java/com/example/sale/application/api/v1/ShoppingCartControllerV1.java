package com.example.sale.application.api.v1;

import com.example.common.command.CommandBus;
import com.example.sale.application.command.cart.AddItemToCartCommand;
import com.example.sale.application.command.cart.GetCartCommand;
import com.example.sale.application.command.cart.SubtractItemFromCartCommand;
import com.example.sale.application.vm.CartVm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class ShoppingCartControllerV1 {

    private final CommandBus commandBus;

    @GetMapping("/carts")
    public ResponseEntity<CartVm> getCart() {
        GetCartCommand command = GetCartCommand.of();

        return ResponseEntity.ok(this.commandBus.execute(command));
    }

    @PutMapping("/carts/add-items")
    public ResponseEntity<Void> addItemToCart(@RequestBody AddItemToCartCommand command) {
        this.commandBus.execute(command);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/carts/{cartId}/subtract-items")
    public ResponseEntity<Void> subtractItem(@PathVariable Long cartId, SubtractItemFromCartCommand command) {
        command.setCartId(cartId);
        this.commandBus.execute(command);

        return ResponseEntity.ok().build();
    }

}
