package com.example.catalog.dao.api.v1;

import com.example.catalog.dao.entity.Catalog;
import com.example.catalog.dao.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogServiceV1 {

  private final CatalogRepository repository;

  public Catalog catalog(Long id) {

    return repository.findByCatalogId(id);
  }

}
