package com.example.catalog.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "inventory")
@RequestMapping("/api/inventory")
public interface InventoryFeignClient {


}
