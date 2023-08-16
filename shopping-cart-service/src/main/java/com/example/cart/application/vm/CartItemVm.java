package com.example.cart.application.vm;

import com.example.cart.domain.cart.CartItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter(AccessLevel.PRIVATE)
public class CartItemVm {

    private Long productId;

    private Long price;

    private Long quantity;

    private String name;

    private String image;

    public static CartItemVm of(CartItem item) {
        CartItemVm cartItem = new CartItemVm();
        cartItem.setProductId(item.getProductId());
        cartItem.setPrice(item.getPrice());
        cartItem.setQuantity(item.getQuantity());
        cartItem.setName(item.getName());
        cartItem.setImage(item.getImage());

        return cartItem;
    }
}
