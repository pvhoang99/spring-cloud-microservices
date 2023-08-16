package com.example.cart.domain.cart;

public interface CartRepository {

    void save(Cart cart);

    Cart findActiveCart(String username);

    Cart getById(Long id);
}
