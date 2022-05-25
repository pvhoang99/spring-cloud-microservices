package com.example.catalog.api.v1;

import com.example.catalog.dao.entity.Product;
import com.example.catalog.dao.repository.ProductRepository;
import com.example.catalog.dto.Catalog;
import com.example.catalog.dto.EditProduct;
import com.example.common.utils.CastUtils;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogServiceV1 {

  private final ProductRepository productRepository;

  public Catalog getCatalog() {
    Set<Product> productSet = CastUtils.convertIterableToSet(productRepository.findAll());
    return new Catalog(productSet);
  }

  public List<Product> getProducts(String productIds) {
    String[] ids = productIds.split(",");
    return productRepository.findProductsByListId(ids);
  }

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  @PostAuthorize("returnObject.createdBy == authentication.name")
  public Product editProduct(EditProduct editProduct) {

    Product product = productRepository.findByProductId(editProduct.getProductId());

    int quantity = product.getQuantity() + editProduct.getAmount();
    if (quantity < 0) {
      throw new RuntimeException("quantity < 0" + quantity);
    }

    product.setQuantity(quantity);
    return productRepository.save(product);
  }

  @PostFilter("filterObject.createdBy == authentication.name")
  public Set<Product> getProductOwn() {

    return CastUtils.convertIterableToSet(productRepository.findAll());

  }
}
