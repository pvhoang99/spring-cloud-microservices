package com.example.cart.application.query.cart;

import com.example.cart.application.vm.CartVm;
import com.example.cart.domain.cart.Cart;
import com.example.cart.domain.cart.CartRepository;
import com.example.cart.domain.cart.CartService;
import com.example.common.query.QueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetCartByTransactionIdHandler implements QueryHandler<GetCartByTransactionIdQuery, CartVm> {
    private final CartRepository cartRepository;
    private final CartService cartService;

    @Override
    @Transactional
    public CartVm handle(GetCartByTransactionIdQuery query) {
        String transactionId = query.getTransactionId();
        Cart cart = this.cartRepository.getByTransactionId(transactionId);
        cart.reloadCart(this.cartService);
        this.cartRepository.save(cart);

        return CartVm.of(cart);
    }
}
