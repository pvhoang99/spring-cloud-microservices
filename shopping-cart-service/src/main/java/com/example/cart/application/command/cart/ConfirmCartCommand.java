package com.example.cart.application.command.cart;

import com.example.common.command.Command;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ConfirmCartCommand implements Command<Void> {

    @Setter
    @NotNull
    private Long cartId;

}
