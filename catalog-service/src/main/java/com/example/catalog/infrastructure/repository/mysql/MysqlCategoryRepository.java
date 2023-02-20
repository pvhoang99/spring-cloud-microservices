package com.example.catalog.infrastructure.repository.mysql;

import com.example.catalog.domain.category.Category;
import com.example.catalog.domain.category.CategoryRepository;
import com.example.catalog.infrastructure.repository.jpa.JpaCategoryRepository;
import com.example.common.exception.EntityNotFoundException;
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
    public Category get(Long id) {
        return this.jpaCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("category.not.found"));
    }

}
