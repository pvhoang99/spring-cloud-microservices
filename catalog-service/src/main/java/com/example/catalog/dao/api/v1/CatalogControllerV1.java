package com.example.catalog.dao.api.v1;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CatalogControllerV1 {

  private final CatalogServiceV1 catalogService;

  @GetMapping("/catalog/{id}")
  public ResponseEntity<?> getCatalog(@PathVariable("id") Long id) {
    return ResponseEntity.ok(catalogService.catalog(id));
  }

  @RequestMapping(path = "/products", method = RequestMethod.GET, name = "getAvailableInventoryForProductIds")
  public ResponseEntity<?> getProductForProductIds(@RequestParam("productIds") String productIds) {
    return Optional.ofNullable(catalogService.getProducts(productIds))
        .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
