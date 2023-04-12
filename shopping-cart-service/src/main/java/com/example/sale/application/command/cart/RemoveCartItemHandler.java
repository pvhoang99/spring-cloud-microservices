package com.example.sale.application.command.cart;

import com.example.common.command.CommandHandler;
import com.example.sale.domain.cart.Cart;
import com.example.sale.domain.cart.CartItem;
import com.example.sale.domain.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoveCartItemHandler implements CommandHandler<RemoveCartItemCommand, Void> {

    private final CartRepository cartRepository;

    @Override
    @Transactional
    public Void handle(RemoveCartItemCommand command) {
        final Cart cart = this.cartRepository.getCartById(command.getCartId());
        final CartItem cartItem = this.cartRepository.getCartItemById(command.getItemId());
        cart.removeCartItem(cartItem);
        this.cartRepository.saveCart(cart);

        return null;
    }

}
