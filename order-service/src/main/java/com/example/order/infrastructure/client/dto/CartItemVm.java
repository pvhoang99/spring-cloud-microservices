package com.example.order.infrastructure.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemVm {

    private Long productId;

    private Long price;

    private Long quantity;

    private String name;

    private String image;

}
