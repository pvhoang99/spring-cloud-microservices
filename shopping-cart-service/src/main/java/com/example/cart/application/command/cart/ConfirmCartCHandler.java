package com.example.cart.application.command.cart;

import com.example.cart.domain.cart.Cart;
import com.example.cart.domain.cart.CartRepository;
import com.example.common.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConfirmCartCHandler implements CommandHandler<ConfirmCartCommand, Void> {

    private final CartRepository cartRepository;

    @Override
    @Transactional
    public Void handle(ConfirmCartCommand command) {
        Cart cart = this.cartRepository.getById(command.getCartId());
        cart.confirmCart();
        this.cartRepository.save(cart);

        return null;
    }
}
