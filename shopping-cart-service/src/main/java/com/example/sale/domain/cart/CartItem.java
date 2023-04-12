package com.example.sale.domain.cart;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class CartItem {

    @Id
    private Long id;

    private Long productId;

    private Long quantity;

    public void updateQuantity(Long quantity) {
        this.quantity = quantity;
    }

}
