package com.example.sale.infrastructure.repository;

import com.example.sale.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartJpaRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long id);

}
