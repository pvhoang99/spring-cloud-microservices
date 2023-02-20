package com.example.inventory.infrastructure.repository.jpa;

import com.example.inventory.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, String> {

}
