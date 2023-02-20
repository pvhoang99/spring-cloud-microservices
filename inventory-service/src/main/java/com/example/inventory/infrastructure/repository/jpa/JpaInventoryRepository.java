package com.example.inventory.infrastructure.repository.jpa;

import com.example.inventory.domain.inventory.Inventory;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaInventoryRepository extends JpaRepository<Inventory, UUID> {

}
