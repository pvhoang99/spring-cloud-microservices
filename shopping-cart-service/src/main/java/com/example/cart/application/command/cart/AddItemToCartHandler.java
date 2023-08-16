package com.example.cart.application.command.cart;

import com.example.common.command.CommandHandler;
import com.example.cart.domain.cart.Cart;
import com.example.cart.domain.cart.CartRepository;
import com.example.cart.domain.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddItemToCartHandler implements CommandHandler<AddItemToCartCommand, Void> {

    private final CartService cartService;
    private final CartRepository cartRepository;

    @Override
    @Transactional
    public Void handle(AddItemToCartCommand command) {
        Cart cart = this.cartService.getCurrentCartOrCreateEmpty();
        cart.addItem(command.getProductId(), command.getQuantity());
        this.cartRepository.save(cart);

        return null;
    }

}
