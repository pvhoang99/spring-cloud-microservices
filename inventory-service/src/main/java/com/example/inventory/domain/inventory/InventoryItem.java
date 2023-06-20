package com.example.inventory.domain.inventory;

import com.example.common.domain.AbstractAuditing;
import com.example.common.valueobject.Money;
import com.example.common.valueobject.MoneyConverter;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class InventoryItem extends AbstractAuditing {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  private UUID id;

  @Embedded
  private InventoryProduct inventoryProduct;

  @Convert(converter = MoneyConverter.class)
  @Column(name = "money")
  private Money money;

  @Column(name = "quantity")
  private long quantity;

  @Column(name = "note")
  private String note;

  public static InventoryItem of(InventoryProduct inventoryProduct, long quantity, String note) {
    InventoryItem inventoryItem = new InventoryItem();
    inventoryItem.setInventoryProduct(inventoryProduct);
    inventoryItem.setQuantity(quantity);
    inventoryItem.setNote(note);
    inventoryItem.calculateMoney();

    return inventoryItem;
  }

  public Money getMoney() {
    this.calculateMoney();

    return this.money;
  }

  private void calculateMoney() {
    Money totalMoneyOnItem = this.inventoryProduct.getMoney().mul(this.quantity);
    this.setMoney(totalMoneyOnItem);
  }

}
