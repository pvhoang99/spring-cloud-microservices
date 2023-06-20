package com.example.catalog.application.vm;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductVm {

  private Long id;

  private String name;

  private Long price;

  private String description;

  private String image;

  private Long categoryId;

  public ProductVm(Long id, String name, Long price, String description, String image, Long categoryId) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.description = description;
    this.image = image;
    this.categoryId = categoryId;
  }

}
