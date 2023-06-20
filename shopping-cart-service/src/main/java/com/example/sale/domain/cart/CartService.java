package com.example.sale.domain.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Map<Long, Long> collectPriceProduct(Set<Long> productIds) {
        //call product service to collect price

        return null;
    }

}
