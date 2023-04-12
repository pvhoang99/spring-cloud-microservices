package com.example.sale.application.command.cart;

import com.example.common.command.CommandHandler;
import com.example.sale.domain.cart.Cart;
import com.example.sale.domain.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EditCartItemHandler implements CommandHandler<EditCartItemCommand, Void> {

    private final CartRepository cartRepository;

    @Override
    @Transactional
    public Void handle(EditCartItemCommand command) {
        final Cart cart = this.cartRepository.getCartById(command.getCartId());
        cart.editCartItem(command.getCartItemId(), command.getQuantity());
        this.cartRepository.saveCart(cart);

        return null;
    }
}
