package com.example.inventory.infrastructure.repository.mysql;

import com.example.inventory.domain.inventory.Inventory;
import com.example.inventory.domain.inventory.InventoryRepository;
import com.example.inventory.infrastructure.repository.jpa.JpaInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MysqlInventoryRepository implements InventoryRepository {

  private final JpaInventoryRepository jpaInventoryRepository;

  @Override
  public void save(Inventory inventory) {
    this.jpaInventoryRepository.save(inventory);
  }

}
