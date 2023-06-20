package com.example.inventory.domain.inventory;

import com.example.common.domain.ValueObject;
import com.example.common.valueobject.Money;
import com.example.common.valueobject.MoneyConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@Embeddable
@NoArgsConstructor
public class InventoryProduct implements ValueObject {

  @Column(name = "product_code")
  private String code;

  @Column(name = "product_price")
  @Convert(converter = MoneyConverter.class)
  private Money money;

  public static InventoryProduct of(String code, long money) {
    InventoryProduct inventoryProduct = new InventoryProduct();
    inventoryProduct.setCode(code);
    inventoryProduct.setMoney(Money.of(money));

    return inventoryProduct;
  }

}
