package com.example.shoppingcartservice.client;

import com.example.shoppingcartservice.dto.Catalog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "catalog-service")
@RequestMapping("/catalog/api/v1")
public interface CatalogServiceFeignClient {

  @GetMapping("/catalog/{id}")
  Catalog catalog(@PathVariable(value = "id") Long id);
}
