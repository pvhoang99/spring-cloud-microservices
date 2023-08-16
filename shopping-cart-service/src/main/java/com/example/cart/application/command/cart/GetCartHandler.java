package com.example.cart.application.command.cart;

import com.example.common.command.CommandHandler;
import com.example.cart.application.vm.CartVm;
import com.example.cart.domain.cart.Cart;
import com.example.cart.domain.cart.CartRepository;
import com.example.cart.domain.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetCartHandler implements CommandHandler<GetCartCommand, CartVm> {

    private final CartRepository cartRepository;
    private final CartService cartService;

    @Override
    @Transactional
    public CartVm handle(GetCartCommand command) {
        Cart cart = this.cartService.getCurrentCartOrCreateEmpty();
        cart.reloadCart(this.cartService);

        this.cartRepository.save(cart);

        return CartVm.of(cart);
    }

}
