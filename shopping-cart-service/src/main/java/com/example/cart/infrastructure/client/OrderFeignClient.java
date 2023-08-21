package com.example.cart.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "order", path = "/api/order")
public interface OrderFeignClient {
}
