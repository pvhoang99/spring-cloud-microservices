package com.example.inventory.domain.inventory;

import com.example.common.domain.AggregateRoot;
import com.example.common.valueobject.Money;
import com.example.common.valueobject.MoneyConverter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter(AccessLevel.PRIVATE)
public class Inventory extends AggregateRoot {

  @Id
  private String id;

  @OneToMany
  private List<InventoryItem> inventoryItems;

  @Convert(converter = MoneyConverter.class)
  private Money totalMoney;

  public static Inventory of() {
    Inventory inventory = new Inventory();
    inventory.generateId();
    inventory.setTotalMoney(Money.of(0L));
    inventory.setInventoryItems(Collections.emptyList());

    return inventory;
  }

  public void add(InventoryItem item) {
    this.inventoryItems.add(item);
  }

  public void calculateMoney() {
    Money money = new Money();
    for (InventoryItem inventoryItem : this.inventoryItems) {
      money.add(inventoryItem.getMoney());
    }

    this.setTotalMoney(money);
  }

  private void generateId() {
    this.setId(UUID.randomUUID().toString());
  }

}
