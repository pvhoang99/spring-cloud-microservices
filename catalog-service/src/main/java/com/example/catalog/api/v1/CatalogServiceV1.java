package com.example.catalog.api.v1;

import com.example.catalog.dao.entity.Catalog;
import com.example.catalog.dao.entity.Product;
import com.example.catalog.dao.repository.CatalogRepository;
import com.example.catalog.dao.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogServiceV1 {

  private final CatalogRepository catalogRepository;
  private final ProductRepository productRepository;

  public Catalog catalog(Long id) {

    return catalogRepository.findByCatalogId(id);
  }

  public List<Product> getProducts(String productIds) {
    String[] ids = productIds.split(",");
    return productRepository.findProductsByListId(ids);

  }

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }
}
