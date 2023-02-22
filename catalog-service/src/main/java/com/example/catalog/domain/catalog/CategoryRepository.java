package com.example.catalog.domain.catalog;


public interface CategoryRepository {

    void save(Category category);

    Category get(String id);

}
