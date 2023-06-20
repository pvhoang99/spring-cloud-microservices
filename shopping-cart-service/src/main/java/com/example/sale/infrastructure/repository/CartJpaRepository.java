package com.example.sale.infrastructure.repository;

import com.example.sale.domain.cart.Cart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartJpaRepository extends JpaRepository<Cart, Long> {

  Optional<Cart> findByUserId(Long id);

}
