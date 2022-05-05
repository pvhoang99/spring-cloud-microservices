package com.example.catalog.dao.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "PRODUCT")
@Setter
@Getter
@NoArgsConstructor
public class Product implements Serializable {

  @Id
  @GeneratedValue
  private Long id;
  private String name, productId, description;
  private Double unitPrice;
  private int quantity;

  public Product(String name, String productId, Double unitPrice, int quantity) {
    this.name = name;
    this.productId = productId;
    this.unitPrice = unitPrice;
    this.quantity = quantity;
  }

  public Product(String name, String productId, String description, Double unitPrice) {
    this.name = name;
    this.productId = productId;
    this.description = description;
    this.unitPrice = unitPrice;
  }

}
