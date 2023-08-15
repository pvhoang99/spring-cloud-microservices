package com.example.cart.domain.cart;

import com.example.common.exception.BadRequestException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Table(name = "cart_item")
@Getter
@EqualsAndHashCode
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id", unique = true)
    private Long productId;

    @Column(name = "quantity")
    private Long quantity;

    @Transient
    private Long price;

    @Transient
    private String image;

    @Transient
    private String name;

    public static CartItem createNewItem(Long productId) {
        CartItem cartItem = new CartItem();
        cartItem.productId = productId;
        cartItem.quantity = 0L;

        return cartItem;
    }

    public void addInfo(Long price, String image, String name) {
        this.price = price;
        this.image = image;
        this.name = name;
    }

    public void addQuantity(Long quantity) {
        this.quantity += quantity;
    }

    public void subtractQuantity(Long quantity) {
        if (this.quantity < quantity) {
            throw new BadRequestException("Số lượng của item không đủ để trừ");
        }
        this.quantity -= quantity;
    }

}
