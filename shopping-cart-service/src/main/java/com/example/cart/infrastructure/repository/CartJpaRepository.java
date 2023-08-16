package com.example.cart.infrastructure.repository;

import com.example.cart.domain.cart.Cart;
import com.example.cart.domain.cart.Status;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartJpaRepository extends JpaRepository<Cart, Long> {

  Optional<Cart> findByUsernameAndStatus(String username, Status status);

}
