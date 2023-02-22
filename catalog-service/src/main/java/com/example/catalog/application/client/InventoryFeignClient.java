package com.example.catalog.application.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "inventory")
@RequestMapping("/api/inventory")
public interface InventoryFeignClient {


}
