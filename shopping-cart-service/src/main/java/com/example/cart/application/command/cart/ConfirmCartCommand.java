package com.example.cart.application.command.cart;

import com.example.common.command.Command;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class ConfirmCartCommand implements Command<Void> {

    @NotNull
    private Long cartId;

}
