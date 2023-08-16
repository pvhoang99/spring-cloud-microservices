package com.example.cart.application.command.cart;

import com.example.common.command.Command;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddItemToCartCommand implements Command<Void> {

    @NotNull
    private Long productId;

    @NotNull
    private Long quantity;

}
