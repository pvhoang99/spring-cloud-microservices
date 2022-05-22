package com.example.catalog.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Transient;

@NodeEntity(label = "PRODUCT")
@Setter
@Getter
@NoArgsConstructor
public class Product extends BaseEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String description;

  @Transient
  private String fileId;

  @Relationship(value = "HAS_IMAGE")
  private FileEntity fileEntity;
}
