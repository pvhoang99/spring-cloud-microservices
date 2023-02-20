package com.example.inventory.domain.category;


public interface CategoryRepository {

    void save(Category category);

    Category get(String id);

}
