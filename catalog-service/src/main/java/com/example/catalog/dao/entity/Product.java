package com.example.catalog.dao.entity;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Product extends BaseEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String name, productId, description;

  private Double unitPrice;

  @Transient
  private String imageId;

  @Relationship(value = "HAS_IMAGE", direction = Relationship.OUTGOING)
  private File image;

  private int quantity;

}
