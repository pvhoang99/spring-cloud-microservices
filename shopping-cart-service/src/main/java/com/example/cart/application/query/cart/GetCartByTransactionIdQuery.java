package com.example.cart.application.query.cart;

import com.example.cart.application.vm.CartVm;
import com.example.common.query.Query;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class GetCartByTransactionIdQuery implements Query<CartVm> {

    @Nonnull
    private String transactionId;

}
