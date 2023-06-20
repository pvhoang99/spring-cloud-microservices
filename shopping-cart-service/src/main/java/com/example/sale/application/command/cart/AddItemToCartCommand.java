package com.example.sale.application.command.cart;

import com.example.common.command.Command;
import lombok.Getter;

@Getter
public class AddItemToCartCommand implements Command<Void> {

    private Long productId;

    private Long quantity;

}
