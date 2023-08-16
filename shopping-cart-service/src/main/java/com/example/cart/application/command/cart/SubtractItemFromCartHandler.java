package com.example.cart.application.command.cart;

import com.example.common.command.CommandHandler;
import com.example.cart.domain.cart.Cart;
import com.example.cart.domain.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubtractItemFromCartHandler implements CommandHandler<SubtractItemFromCartCommand, Void> {

    private final CartRepository cartRepository;

    @Override
    @Transactional
    public Void handle(SubtractItemFromCartCommand command) {
        Cart cart = this.cartRepository.getById(command.getCartId());
        cart.subtractItem(command.getProductId(), command.getQuantity());
        this.cartRepository.save(cart);

        return null;
    }

}
