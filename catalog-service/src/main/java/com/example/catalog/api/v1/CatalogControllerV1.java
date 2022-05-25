package com.example.catalog.api.v1;

import com.example.catalog.dao.entity.Product;
import com.example.catalog.dto.EditProduct;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CatalogControllerV1 {

  private final CatalogServiceV1 catalogService;

  @GetMapping("/catalog")
  public ResponseEntity<?> getCatalog() {
    return ResponseEntity.ok(catalogService.getCatalog());
  }

  @RequestMapping(path = "/products", method = RequestMethod.GET, name = "getAvailableInventoryForProductIds")
  public ResponseEntity<?> getProductForProductIds(@RequestParam("productIds") String productIds) {
    return Optional.ofNullable(catalogService.getProducts(productIds))
        .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @RequestMapping(path = "/product/create", method = RequestMethod.POST, name = "createProduct")
  public ResponseEntity<?> createProduct(@RequestBody Product product) {
    return Optional.ofNullable(catalogService.createProduct(product))
        .map(result -> new ResponseEntity<>(result, HttpStatus.CREATED))
        .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
  }

  @RequestMapping(path = "/product/edit", method = RequestMethod.PATCH, name = "editProduct")
  public ResponseEntity<?> editProduct(@RequestBody EditProduct editProduct) {
    return Optional.ofNullable(catalogService.editProduct(editProduct))
        .map(result -> new ResponseEntity<>(result, HttpStatus.CREATED))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @RequestMapping(path = "/product/own", method = RequestMethod.GET, name = "getProductOwn")
  public ResponseEntity<?> getProductOwn() {
    return Optional.ofNullable(catalogService.getProductOwn())
        .map(result -> new ResponseEntity<>(result, HttpStatus.CREATED))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
