package com.example.sale.application.command.cart;

import com.example.common.command.CommandHandler;
import com.example.sale.application.vm.CartVm;
import com.example.sale.domain.cart.Cart;
import com.example.sale.domain.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCartHandler implements CommandHandler<GetCartCommand, CartVm> {

    private final CartRepository cartRepository;

    @Override
    public CartVm handle(GetCartCommand command) {
        Cart cart = this.cartRepository.findActiveCart(command.getUserId());
        if (cart == null) {
            cart = Cart.createEmpty();
        }

        return CartVm.;
    }

}
