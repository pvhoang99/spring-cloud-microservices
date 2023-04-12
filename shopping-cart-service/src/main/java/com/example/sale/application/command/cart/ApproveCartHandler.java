package com.example.sale.application.command.cart;

import com.example.common.command.CommandHandler;
import com.example.sale.domain.cart.Cart;
import com.example.sale.domain.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApproveCartHandler implements CommandHandler<ApproveCartCommand, Void> {

    private final CartRepository cartRepository;

    @Override
    @Transactional
    public Void handle(ApproveCartCommand command) {
        final Cart cart = this.cartRepository.getCartById(command.getCartId());
        cart.approve();
        this.cartRepository.saveCart(cart);

        return null;
    }

}
