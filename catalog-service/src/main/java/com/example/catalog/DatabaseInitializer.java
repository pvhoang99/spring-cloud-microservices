package com.example.catalog;

import com.example.catalog.dao.entity.Catalog;
import com.example.catalog.dao.entity.Product;
import com.example.catalog.dao.repository.CatalogRepository;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatabaseInitializer {

  private final CatalogRepository catalogRepository;

  public void populate() throws Exception {
    Product product1 = new Product("Iphone 12", "IP12", 11.1, 5);
    Product product2 = new Product("Iphone 13", "IP13", 12.99, 10);
    Product product3 = new Product("Iphone 11", "IP11", 10.01, 7);
    Product product4 = new Product("Iphone X", "IPX", 9.99, 6);
    Product product5 = new Product("Iphone 8", "IP8", 8.1, 9);
    Product product6 = new Product("Iphone 7", "IP7", 7.99, 1);
    Catalog catalog = new Catalog(1L, "Phone");
    catalog.setProducts(Arrays.asList(product1, product2, product3, product4, product5, product6));
    catalogRepository.save(catalog);
  }

}
