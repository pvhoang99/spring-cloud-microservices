package com.example.cart.application.api.v1;

import com.example.cart.application.command.cart.AddItemToCartCommand;
import com.example.cart.application.command.cart.ClearCartCommand;
import com.example.cart.application.command.cart.ConfirmCartCommand;
import com.example.cart.application.command.cart.GetCartCommand;
import com.example.cart.application.command.cart.SubtractItemFromCartCommand;
import com.example.cart.application.vm.CartVm;
import com.example.cart.domain.cart.event.CartConfirmedEvent;
import com.example.common.command.CommandBus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/carts")
@RequiredArgsConstructor
public class CartControllerV1 {

    private final CommandBus commandBus;

    @GetMapping
    public ResponseEntity<CartVm> getCart() {
        GetCartCommand command = GetCartCommand.of();
        CartVm cartVm = this.commandBus.execute(command);

        return ResponseEntity.ok(cartVm);
    }

    @PostMapping("/{id}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long id) {
        ClearCartCommand command = ClearCartCommand.of(id);
        this.commandBus.execute(command);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-items")
    public ResponseEntity<Void> addItemToCart(@RequestBody AddItemToCartCommand command) {
        this.commandBus.execute(command);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{cartId}/subtract-items")
    public ResponseEntity<Void> subtractItem(
            @PathVariable Long cartId, SubtractItemFromCartCommand command
    ) {
        command.setCartId(cartId);
        this.commandBus.execute(command);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{cartId}/confirm")
    public ResponseEntity<Void> confirmCart(@PathVariable Long cartId) {
        ConfirmCartCommand command = ConfirmCartCommand.of(cartId);
        this.commandBus.execute(command);

        return ResponseEntity.ok().build();
    }
}
