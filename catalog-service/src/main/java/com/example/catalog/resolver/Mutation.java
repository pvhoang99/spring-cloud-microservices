package com.example.catalog.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.catalog.dao.entity.Catalog;
import com.example.catalog.dao.repository.CatalogRepository;
import com.example.catalog.dao.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

  private final CatalogRepository catalogRepository;
  private final DiseaseRepository diseaseRepository;

  @Autowired
  public Mutation(CatalogRepository catalogRepository, DiseaseRepository diseaseRepository) {
    this.catalogRepository = catalogRepository;
    this.diseaseRepository = diseaseRepository;
  }

  public Catalog createCatalog(String name, String code) {
    Catalog catalog = new Catalog(name, code);
    return catalogRepository.save(catalog);
  }

}
