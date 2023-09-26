package com.example.cart.domain.cart;

import com.example.common.exception.BadRequestException;

public interface CartRepository {

    void save(Cart cart);

    Cart findActiveCart(String username);

    Cart getById(Long id);

    Cart findByTransactionId(String transactionId);

    default Cart getByTransactionId(String transactionId) {
        Cart cart = this.findByTransactionId(transactionId);
        if (cart == null) throw new BadRequestException("Cart không tồn tại trên transaction: " + transactionId);

        return cart;
    }
}
