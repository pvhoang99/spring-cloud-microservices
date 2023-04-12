package com.example.sale.domain.cart;

public interface CartRepository {

    void saveCart(Cart cart);

    void saveCartItem(CartItem cartItem);

    Cart getCartById(Long id);

    CartItem getCartItemById(Long Id);

}
