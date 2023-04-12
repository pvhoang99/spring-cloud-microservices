package com.example.sale.application.command.cart;

import com.example.common.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class CreateCartCommand implements Command<Void> {

}
