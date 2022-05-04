package com.example.inventory.dao.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "ADDRESS")
@Setter
@Getter
@NoArgsConstructor
public class AddressEntity implements Serializable {

  @Id
  @GeneratedValue
  private Long id;
  private String country;
  private String province;
  private String city;
  private String state;
  private String district;
  private String street1;
  private String street2;
  private Integer zipCode;

  public AddressEntity(String street1, String street2, String state,
      String city, String country, Integer zipCode) {
    this.street1 = street1;
    this.street2 = street2;
    this.state = state;
    this.city = city;
    this.country = country;
    this.zipCode = zipCode;
  }
}
