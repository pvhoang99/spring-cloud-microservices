package com.example.catalog.api.v1;

import com.example.catalog.dao.entity.Catalog;
import com.example.catalog.dao.entity.Disease;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CatalogControllerV1 {

  private final CatalogServiceV1 catalogService;

  @RequestMapping(path = "/catalog/create", method = RequestMethod.POST, name = "postCreateCatalog")
  public ResponseEntity<?> postCreateCatalog(@RequestBody Catalog catalog) {
    return Optional.ofNullable(catalogService.createCatalog(catalog))
        .map(rs -> new ResponseEntity<>(rs, HttpStatus.CREATED))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
  }

  @RequestMapping(path = "/disease/create", method = RequestMethod.POST, name = "postCreateDisease")
  public ResponseEntity<?> postCreateDisease(@RequestBody Disease disease) {
    return Optional.ofNullable(catalogService.createDisease(disease))
        .map(rs -> new ResponseEntity<>(rs, HttpStatus.CREATED))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
  }
}
