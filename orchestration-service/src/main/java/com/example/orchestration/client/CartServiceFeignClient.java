package com.example.orchestration.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cart", path = "/api/cart")
public interface CartServiceFeignClient {

    @PostMapping("/v1/carts/reactive/{transactionId}")
    void reactiveConfirmCart(@PathVariable String transactionId);

}
