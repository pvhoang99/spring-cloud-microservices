package com.example.inventory.api.v1;


import com.example.inventory.dao.entity.InventoryEntity;
import com.example.inventory.dao.entity.ProductEntity;
import com.example.inventory.dao.repository.InventoryRepository;
import com.example.inventory.dao.repository.ProductRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceV1 {

  private final InventoryRepository inventoryRepository;
  private final ProductRepository productRepository;

  @HystrixCommand(fallbackMethod = "getProductFallback")
  public ProductEntity getProduct(String productId) {
    ProductEntity product;

    product = productRepository.getProductEntityByProductId(productId);

    if (product != null) {
      Stream<InventoryEntity> availableInventory = inventoryRepository.getAvailableInventoryForProduct(
          productId).stream();
      product.setInStock(availableInventory.findAny().isPresent());
    }

    return product;
  }

  public ProductEntity getProductFallback(String productId) {
    return null;
  }

  public List<InventoryEntity> getAvailableInventoryForProductIds(String productIds) {
    List<InventoryEntity> inventoryList;

    inventoryList = inventoryRepository.getAvailableInventoryForProductList(productIds.split(","));

    return new ArrayList<>(inventoryList);
  }
}
