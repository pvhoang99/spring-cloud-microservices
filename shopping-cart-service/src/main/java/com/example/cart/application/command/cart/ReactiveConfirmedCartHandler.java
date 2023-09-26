package com.example.cart.application.command.cart;

import com.example.cart.domain.cart.Cart;
import com.example.cart.domain.cart.CartRepository;
import com.example.common.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReactiveConfirmedCartHandler implements CommandHandler<ReactiveConfirmedCartCommand, Void> {
    private final CartRepository cartRepository;

    @Override
    public Void handle(ReactiveConfirmedCartCommand command) {
        String transactionId = command.getTransactionId();
        Cart cart = this.cartRepository.findByTransactionId(transactionId);
        cart.reactiveCart();
        this.cartRepository.save(cart);

        return null;
    }
}
