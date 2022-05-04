package com.example.shoppingcartservice.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Address;

@Getter
@Setter
@NoArgsConstructor
public class Order {

  private Address shipToAddress;
  private List<Product> products;
  private Double totalMoney;

}
