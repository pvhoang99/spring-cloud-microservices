package com.example.shoppingcartservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "order-service")
@RequestMapping("/order/api/v1")
public interface OrderServiceFeignClient {

}
