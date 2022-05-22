package com.example.catalog;

import com.example.catalog.dao.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatabaseInitializer {

  private final CatalogRepository catalogRepository;

  public void populate() throws Exception {
  }

}
