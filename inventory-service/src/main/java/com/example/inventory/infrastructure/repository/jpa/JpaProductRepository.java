package com.example.inventory.infrastructure.repository.jpa;

import com.example.inventory.domain.product.Product;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, String> {

    Integer countAllByCodeIn(Set<String> ids);

    default boolean existsAllById(Set<String> ids) {
        return countAllByCodeIn(ids).equals(ids.size());
    }

}
