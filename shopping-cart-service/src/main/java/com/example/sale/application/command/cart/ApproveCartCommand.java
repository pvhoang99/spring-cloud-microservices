package com.example.sale.application.command.cart;

import com.example.common.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApproveCartCommand implements Command<Void> {

    private Long cartId;

}
