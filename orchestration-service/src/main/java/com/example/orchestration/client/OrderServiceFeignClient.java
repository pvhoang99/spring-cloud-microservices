package com.example.orchestration.client;

import com.example.orchestration.client.dto.CreateOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service", path = "/api/order")
public interface OrderServiceFeignClient {

    @PostMapping("/v1/orders")
    void createOrder(@RequestBody CreateOrderDTO createOrderDTO);

}
