package com.example.inventory.dao.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "WAREHOUSE")
@Setter
@Getter
@NoArgsConstructor
public class WarehouseEntity implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @Relationship(type = "HAS_ADDRESS")
  private AddressEntity addressEntity;

  public WarehouseEntity(String name) {
    this.name = name;
  }

}
