package com.example.cart.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "orchestration", path = "/api/orchestration")
public interface OrchestrationFeignClient {
}
