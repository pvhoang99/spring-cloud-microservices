package com.example.catalog.dao.repository;

import com.example.catalog.dao.entity.Catalog;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {

  Optional<Catalog> findByCode(String code);

  boolean existsByCode(String code);
}
