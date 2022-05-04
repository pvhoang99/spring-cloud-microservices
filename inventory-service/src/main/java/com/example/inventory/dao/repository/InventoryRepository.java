package com.example.inventory.dao.repository;

import com.example.inventory.dao.entity.InventoryEntity;
import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends Neo4jRepository<InventoryEntity, Long> {

  @Query("MATCH (product:Product),\n" +
      "\t(product)<-[:PRODUCT_TYPE]-(inventory:Inventory)\n" +
      "WHERE product.productId =$productId AND NOT (inventory)<-[:CONTAINS_PRODUCT]-()\n" +
      "RETURN inventory")
  List<InventoryEntity> getAvailableInventoryForProduct(@Param("productId") String productId);

  @Query("MATCH (product:Product),\n" +
      "\t(product)<-[:PRODUCT_TYPE]-(inventory:Inventory)\n" +
      "WHERE product.productId in $productIds AND NOT (inventory)<-[:CONTAINS_PRODUCT]-()\n" +
      "RETURN inventory")
  List<InventoryEntity> getAvailableInventoryForProductList(
      @Param("productIds") String[] productIds);

  @Query("MATCH (product:Product),\n" +
      "\t(product)<-[:PRODUCT_TYPE]-(inventory:Inventory),\n" +
      "    (inventory)-[:STOCKED_IN]->(:Warehouse { name: $warehouseName })\n" +
      "WHERE product.productId = $productId AND NOT (inventory)<-[:CONTAINS_PRODUCT]-()\n" +
      "RETURN inventory")
  List<InventoryEntity> getAvailableInventoryForProductAndWarehouse(
      @Param("productId") String productId,
      @Param("warehouseName") String warehouseName);
}
