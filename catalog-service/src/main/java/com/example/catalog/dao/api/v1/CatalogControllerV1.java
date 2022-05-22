package com.example.catalog.dao.api.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
