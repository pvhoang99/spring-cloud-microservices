package com.example.cart.application.command.cart;

import com.example.common.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class ClearCartCommand implements Command<Void> {

    private Long id;

}
