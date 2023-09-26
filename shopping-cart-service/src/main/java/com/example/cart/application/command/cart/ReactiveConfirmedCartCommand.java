package com.example.cart.application.command.cart;

import com.example.common.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class ReactiveConfirmedCartCommand implements Command<Void> {

    private String transactionId;

}
