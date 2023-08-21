package com.example.order.application.command;

import com.example.common.command.Command;
import lombok.Getter;

@Getter
public class CreateOrderCommand implements Command<Void> {
    private Long cartId;



}
