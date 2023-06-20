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
  public void save(Cart cart) {
    this.cartJpaRepository.save(cart);
  }

  @Override
  public Cart findActiveCart(Long userId) {
    return this.cartJpaRepository.findByUserId(userId).orElse(null);
  }

  @Override
  public Cart getById(Long id) {
    return this.cartJpaRepository.getOne(id);
  }

}
