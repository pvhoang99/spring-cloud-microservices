package com.example.inventory.dao.entity;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "SHIPMENT")
@Getter
@Setter
@NoArgsConstructor
public class ShipmentEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Relationship(type = "CONTAINS_PRODUCT")
  private Set<InventoryEntity> inventories = new HashSet<>();

  @Relationship(type = "SHIP_TO")
  private AddressEntity deliveryAddressEntity;

  @Relationship(type = "SHIP_FROM")
  private WarehouseEntity fromWarehouseEntity;

  private ShipmentStatus shipmentStatus;

  public ShipmentEntity(Set<InventoryEntity> inventories, AddressEntity deliveryAddress,
      WarehouseEntity fromWarehouse, ShipmentStatus shipmentStatus) {
    this.inventories = inventories;
    this.deliveryAddressEntity = deliveryAddress;
    this.fromWarehouseEntity = fromWarehouse;
    this.shipmentStatus = shipmentStatus;
  }

  public enum ShipmentStatus {
    PENDING,
    SHIPPED,
    DELIVERED
  }
}