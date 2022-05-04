package com.example.inventory.dao.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "INVENTORY")
@Setter
@Getter
@NoArgsConstructor
public class InventoryEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String inventoryNumber;

  @Relationship(type = "PRODUCT_TYPE")
  private ProductEntity productEntity;

  @Relationship(type = "STOCKED_IN")
  private WarehouseEntity warehouseEntity;

  private InventoryStatus status;

  public InventoryEntity(String inventoryNumber, ProductEntity product) {
    this.inventoryNumber = inventoryNumber;
    this.productEntity = product;
  }

  public InventoryEntity(String inventoryNumber, ProductEntity product, WarehouseEntity warehouse,
      InventoryStatus status) {
    this.inventoryNumber = inventoryNumber;
    this.productEntity = product;
    this.warehouseEntity = warehouse;
    this.status = status;
  }

  public static enum InventoryStatus {
    IN_STOCK,
    ORDERED,
    RESERVED,
    SHIPPED,
    DELIVERED
  }

}


