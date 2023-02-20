package com.example.catalog.application.client;

import com.example.catalog.application.client.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "inventory")
@RequestMapping("/api/inventory")
public interface InventoryFeignClient {

    @GetMapping("/v1/products/{code}")
    public ProductDTO getOne(@PathVariable String code);


}
