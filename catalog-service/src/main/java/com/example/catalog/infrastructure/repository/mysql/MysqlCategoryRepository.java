package com.example.catalog.infrastructure.repository.mysql;

import com.example.catalog.domain.catalog.Category;
import com.example.catalog.domain.catalog.CategoryRepository;
import com.example.catalog.infrastructure.repository.jpa.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MysqlCategoryRepository implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;

    @Override
    public void save(Category category) {
        this.jpaCategoryRepository.save(category);
    }

    @Override
    public Category get(String id) {
        return this.jpaCategoryRepository.getOne(id);
    }

}
