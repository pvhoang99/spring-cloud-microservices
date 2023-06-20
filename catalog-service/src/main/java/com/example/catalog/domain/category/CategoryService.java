package com.example.catalog.domain.category;

import com.example.catalog.infrastructure.repository.jpa.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final JpaCategoryRepository jpaCategoryRepository;

  public boolean existById(Long id) {
    return jpaCategoryRepository.existsById(id);
  }

}
