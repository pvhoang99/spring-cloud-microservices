package com.example.inventory.domain.product;

import com.example.common.exception.EntityNotFoundException;
import com.example.inventory.infrastructure.repository.jpa.JpaProductRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final JpaProductRepository jpaProductRepository;

    public void checkExistAll(Set<String> codes) {
        if (!this.jpaProductRepository.existsAllById(codes)) {
            throw new EntityNotFoundException("product.not.exist");
        }
    }

    public boolean existByCode(String code) {
        return this.jpaProductRepository.existsById(code);
    }


}
