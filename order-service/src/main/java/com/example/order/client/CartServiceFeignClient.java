package com.example.order.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "order-service", path = "/api/order")
public interface CartServiceFeignClient {

}
