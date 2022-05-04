package com.example.inventory.dao.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Transient;

@NodeEntity(label = "PRODUCT")
@Setter
@Getter
@NoArgsConstructor
public class ProductEntity implements Serializable {

  @Id
  @GeneratedValue
  private Long id;
  private String name, productId, description;
  private Double unitPrice;
  @Transient
  private Boolean inStock;

  public ProductEntity(String name, String productId, Double unitPrice) {
    this.name = name;
    this.productId = productId;
    this.unitPrice = unitPrice;
  }

  public ProductEntity(String name, String productId, String description, Double unitPrice) {
    this.name = name;
    this.productId = productId;
    this.description = description;
    this.unitPrice = unitPrice;
  }

}
