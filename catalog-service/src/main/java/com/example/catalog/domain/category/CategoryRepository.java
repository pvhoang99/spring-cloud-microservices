package com.example.catalog.domain.category;


public interface CategoryRepository {

  void save(Category category);

  Category get(Long id);

}
