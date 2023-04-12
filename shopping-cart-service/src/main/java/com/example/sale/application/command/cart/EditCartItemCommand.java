package com.example.sale.application.command.cart;

import com.example.common.command.Command;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
public class EditCartItemCommand implements Command<Void> {

    @JsonIgnore
    @Setter
    private Long cartId;

    @JsonIgnore
    @Setter
    private Long cartItemId;

    private Long quantity;


}
