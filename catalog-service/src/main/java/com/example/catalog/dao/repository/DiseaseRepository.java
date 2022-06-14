package com.example.catalog.dao.repository;

import com.example.catalog.dao.entity.Disease;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

  @Query("select e from Disease e where e.id in :#{#ids}")
  List<Disease> getDiseaseByIds(List<Long> ids);

}
