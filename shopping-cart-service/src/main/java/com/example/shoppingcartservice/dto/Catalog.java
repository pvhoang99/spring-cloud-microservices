package com.example.shoppingcartservice.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Catalog {

  private Long id;

  private String name;

  private Set<Product> products = new HashSet<>();

}
