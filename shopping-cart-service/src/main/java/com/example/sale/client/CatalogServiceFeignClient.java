package com.example.sale.client;

import com.example.sale.dto.Catalog;
import com.example.sale.dto.Product;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "catalog-service")
@RequestMapping("/catalog/api/v1")
public interface CatalogServiceFeignClient {

  @GetMapping("/catalog")
  Catalog getCatalog();

  @GetMapping("/products")
  List<Product> getProductByProductIds(@RequestParam("productIds") String productIds);
}
