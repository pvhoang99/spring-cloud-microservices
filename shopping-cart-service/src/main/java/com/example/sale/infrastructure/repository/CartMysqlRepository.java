package com.example.sale.infrastructure.repository;

import com.example.sale.domain.cart.Cart;
import com.example.sale.domain.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartMysqlRepository implements CartRepository {

    private final CartJpaRepository cartJpaRepository;

    @Override
    public void saveCart(Cart cart) {

    }

    @Override
    public Cart findActiveCart(Long userId) {
        return this.cartJpaRepository.findByUserId(userId).orElse(null);
    }

}
