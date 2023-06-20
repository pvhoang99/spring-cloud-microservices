package com.example.sale.domain.cart;

public interface CartRepository {

    void saveCart(Cart cart);

    Cart findActiveCart(Long userId);
}
