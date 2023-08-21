package com.example.order.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "auth")
public interface AuthServiceFeignClient {
}
