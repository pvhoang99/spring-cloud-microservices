package com.example.catalog;

import com.example.catalog.dao.entity.Catalog;
import com.example.catalog.dao.entity.Disease;
import com.example.catalog.dao.repository.CatalogRepository;
import com.example.catalog.dao.repository.DiseaseRepository;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatabaseInitializer {

  private final CatalogRepository catalogRepository;
  private final DiseaseRepository diseaseRepository;

  public void populate() {

    if (!catalogRepository.existsByCode("000")) {
      Catalog catalog = new Catalog();
      catalog.setCode("000");
      catalog.setName("Benh");
      catalogRepository.save(catalog);

      Disease camCum = Disease.builder()
          .code("CC")
          .name("Cam Cum")
          .catalogId(catalog.getId())
          .build();
      Disease ungThu = Disease.builder()
          .code("UT")
          .name("Ung Thu")
          .catalogId(catalog.getId())
          .build();
      diseaseRepository.saveAll(Arrays.asList(ungThu, camCum));

    }
  }

}
