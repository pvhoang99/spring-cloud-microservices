package com.example.order.client.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class CartItemVm {

    private Long productId;

    private Long price;

    private Long quantity;

    private String name;

    private String image;

}
