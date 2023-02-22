package com.example.inventory.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "catalog")
public interface CatalogFeignClient {



}
