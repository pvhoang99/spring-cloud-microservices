package com.example.catalog.api.v1;

import com.example.catalog.dao.entity.Catalog;
import com.example.catalog.dao.entity.Disease;
import com.example.catalog.dao.repository.CatalogRepository;
import com.example.catalog.dao.repository.DiseaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogServiceV1 {

  private final CatalogRepository catalogRepository;
  private final DiseaseRepository diseaseRepository;

  public Catalog createCatalog(Catalog catalog) {
    return catalogRepository.save(catalog);
  }

  public Disease createDisease(Disease disease) {
    Catalog catalog = catalogRepository.findById(disease.getCatalogId())
        .orElseThrow(() -> new RuntimeException(""));
    disease.setCatalog(catalog);
    return diseaseRepository.save(disease);
  }

}
