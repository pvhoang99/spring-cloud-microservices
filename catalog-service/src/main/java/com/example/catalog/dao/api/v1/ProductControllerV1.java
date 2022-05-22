package com.example.catalog.dao.api.v1;

import com.example.catalog.dao.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductControllerV1 {

  private final ProductServiceV1 productService;

  @PostMapping("/create")
  public ResponseEntity<?> createProduct(@RequestBody Product product) {
    return ResponseEntity.ok(productService.createProduct(product));

  }

  @GetMapping("/all")
  public ResponseEntity<?> getAllProductOwn() {
    return ResponseEntity.ok(productService.getAllProduct());
  }

}
