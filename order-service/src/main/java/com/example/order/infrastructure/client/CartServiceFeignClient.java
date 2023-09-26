package com.example.order.infrastructure.client;

import com.example.order.infrastructure.client.dto.CartVm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart", path = "/api/cart")
public interface CartServiceFeignClient {

    @GetMapping("/v1/carts/transaction/{transactionId}")
    CartVm getCartByTransactionId(@PathVariable String transactionId);

}
