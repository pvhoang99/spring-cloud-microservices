package com.example.sale.application.command.cart;

import com.example.common.command.Command;
import com.example.sale.application.vm.CartVm;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class GetCartCommand implements Command<CartVm> {
}
