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
  public Cart findActiveCart(String username) {
    return this.cartJpaRepository.findByUsernameAndStatus(username, Status.ACTIVE).orElse(null);
  }

  @Override
  public Cart getById(Long id) {
    return this.cartJpaRepository.getOne(id);
  }

  @Override
  public Cart findByTransactionId(String transactionId) {
    return this.cartJpaRepository.findByTransactionId(transactionId).orElse(null);
  }

}
