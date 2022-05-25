package com.example.catalog.dto;

import com.example.catalog.dao.entity.Product;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Catalog {

  private Set<Product> products = new HashSet<>();

}
