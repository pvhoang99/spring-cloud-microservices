package com.example.catalog.dao.entity;

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
public class Catalog {

  @Id
  @GeneratedValue
  private Long id;
  private Long catalogId;
  private String name;
  @Relationship(type = "HAS_PRODUCT")
  private List<Product> products = new ArrayList<>();

  public Catalog(Long catalogId, String name) {
    this.catalogId = catalogId;
    this.name = name;
  }
}
