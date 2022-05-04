package com.example.shoppingcartservice.client;

import com.example.shoppingcartservice.dto.Catalog;
import com.example.shoppingcartservice.dto.Product;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "inventory-service")
public interface InventoryServiceFeignClient {

  //Get product
  List<Product> getProduct();

  Catalog getCatalog();
}
