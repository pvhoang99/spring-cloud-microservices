package com.example.sale.domain.cart;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Map<Long, Long> collectPriceProduct(List<Long> productIds) {
        //call product service to collect price

        return null;
    }

}
