package com.example.catalog.infrastructure.repository.jpa;

import com.example.catalog.domain.catalog.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, String> {

}
