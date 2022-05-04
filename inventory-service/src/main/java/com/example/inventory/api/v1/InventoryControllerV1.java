package com.example.inventory.api.v1;

import com.example.inventory.dao.entity.ProductEntity;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class InventoryControllerV1 {

  private InventoryServiceV1 inventoryService;

  @Autowired
  public InventoryControllerV1(InventoryServiceV1 inventoryService) {
    this.inventoryService = inventoryService;
  }

  @RequestMapping(path = "/products/{productId}", method = RequestMethod.GET, name = "getProduct")
  public ResponseEntity<ProductEntity> getProduct(@PathVariable("productId") String productId) {
    return Optional.ofNullable(inventoryService.getProduct(productId))
        .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @RequestMapping(path = "/inventory", method = RequestMethod.GET, name = "getAvailableInventoryForProductIds")
  public ResponseEntity<?> getAvailableInventoryForProductIds(
      @RequestParam("productIds") String productIds) {
    return Optional.ofNullable(inventoryService.getAvailableInventoryForProductIds(productIds))
        .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
