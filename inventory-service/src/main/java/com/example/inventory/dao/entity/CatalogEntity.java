package com.example.inventory.dao.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "CATALOG")
@Setter
@Getter
@NoArgsConstructor
public class CatalogEntity {

  @Id
  @GeneratedValue
  private Long id;
  private Long catalogNumber;
  @Relationship(type = "HAS_PRODUCT")
  private List<ProductEntity> products = new ArrayList<>();
  private String name;

  public CatalogEntity(String name, Long catalogNumber) {
    this.name = name;
    this.catalogNumber = catalogNumber;
  }
}
