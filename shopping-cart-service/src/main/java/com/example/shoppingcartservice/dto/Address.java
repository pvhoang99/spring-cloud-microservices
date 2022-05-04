package com.example.shoppingcartservice.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Address implements Serializable {

  private Long id;
  private String country;
  private String province;
  private String city;
  private String state;
  private String district;
  private String street1;
  private String street2;
  private Integer zipCode;

  public Address(String street1, String street2, String state,
      String city, String country, Integer zipCode) {
    this.street1 = street1;
    this.street2 = street2;
    this.state = state;
    this.city = city;
    this.country = country;
    this.zipCode = zipCode;
  }
}
