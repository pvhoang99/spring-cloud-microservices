package com.example.sale.application.command.cart;

import com.example.common.command.CommandHandler;
import com.example.sale.domain.cart.Cart;
import com.example.sale.domain.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubtractItemFromCartHandler implements CommandHandler<SubtractItemFromCartCommand, Void> {

    private final CartRepository cartRepository;

    @Override
    public Void handle(SubtractItemFromCartCommand command) {
        Cart cart = this.cartRepository.getById(command.getCartId());
        cart.subtractItem(command.getProductId(), command.getQuantity());

        return null;
    }
}
