package com.example.catalog.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.catalog.dao.entity.Catalog;
import com.example.catalog.dao.repository.CatalogRepository;
import com.example.catalog.dao.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

  private final CatalogRepository catalogRepository;
  private final DiseaseRepository diseaseRepository;

  @Autowired
  public Query(CatalogRepository catalogRepository, DiseaseRepository diseaseRepository) {
    this.catalogRepository = catalogRepository;
    this.diseaseRepository = diseaseRepository;
  }

  public Iterable<Catalog> findAllCatalogs() {
    return catalogRepository.findAll();
  }
}
