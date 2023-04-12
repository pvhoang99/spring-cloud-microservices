package com.example.sale.application.command.cart;

import com.example.common.command.Command;
import com.example.sale.domain.cart.CartItem;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class AddCartItemCommand implements Command<Void> {

    @NotNull
    @Positive
    private Long cartId;

    private CartItem cartItem;

}
