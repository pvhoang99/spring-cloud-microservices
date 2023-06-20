package com.example.inventory.domain.product;

import com.example.common.domain.AggregateRoot;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "product")
@Table(name = "product")
@Getter
@Setter(AccessLevel.PRIVATE)
public class Product extends AggregateRoot {

  private final static int LENGTH_OF_CODE = 6;

  @Id
  private String code;

  @Column(name = "name")
  private String name;


  public static Product of(String code, String name) {
    Product product = new Product();
    product.setCode(code);
    product.setName(name);

    return product;
  }

}
