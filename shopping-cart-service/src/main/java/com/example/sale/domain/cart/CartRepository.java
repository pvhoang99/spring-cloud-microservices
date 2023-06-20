package com.example.sale.domain.cart;

public interface CartRepository {

  void save(Cart cart);

  Cart findActiveCart(Long userId);

  Cart getById(Long id);
}
