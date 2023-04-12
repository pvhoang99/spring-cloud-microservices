package com.example.sale.application.command.cart;

import com.example.common.command.Command;
import lombok.Getter;

@Getter
public class DenyCartCommand implements Command<Void> {

    private Long cartId;

}
