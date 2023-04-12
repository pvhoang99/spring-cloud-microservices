package com.example.sale.application.command.cart;

import com.example.common.command.CommandHandler;
import com.example.sale.domain.cart.Cart;
import com.example.sale.domain.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddCartItemHandler implements CommandHandler<AddCartItemCommand, Void> {

    private final CartRepository cartRepository;

    @Override
    @Transactional
    public Void handle(AddCartItemCommand command) {
        Cart cart = this.cartRepository.getCartById(command.getCartId());
        cart.addCartItem(command.getCartItem());
        this.cartRepository.saveCart(cart);

        return null;
    }

}
