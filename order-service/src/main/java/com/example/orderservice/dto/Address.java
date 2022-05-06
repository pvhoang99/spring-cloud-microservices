package com.example.orderservice.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

  private String country;
  private String city;
  private String district;
  private String street1;
}
