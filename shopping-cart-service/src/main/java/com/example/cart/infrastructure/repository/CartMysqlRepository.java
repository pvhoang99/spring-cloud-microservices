package com.example.cart.infrastructure.repository;

import com.example.cart.domain.cart.Cart;
import com.example.cart.domain.cart.CartRepository;
import com.example.cart.domain.cart.Status;
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
    return this.cartJpaRepository.findByUserIdAndStatus(userId, Status.ACTIVE).orElse(null);
  }

  @Override
  public Cart getById(Long id) {
    return this.cartJpaRepository.getOne(id);
  }

}
