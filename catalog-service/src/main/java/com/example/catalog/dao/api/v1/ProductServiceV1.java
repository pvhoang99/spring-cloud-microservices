package com.example.catalog.dao.api.v1;

import com.example.catalog.dao.entity.FileEntity;
import com.example.catalog.dao.entity.Product;
import com.example.catalog.dao.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceV1 {

  private final ProductRepository productRepository;
  private final FileStorageService fileStorageService;

  public Product createProduct(Product product) {

    String fileId = product.getFileId();
    FileEntity fileEntity = fileStorageService.findById(fileId);
    product.setFileEntity(fileEntity);

    return productRepository.save(product);
  }

  @PostFilter("filterObject.createdBy == authentication.name")
  public Iterable<Product> getAllProduct() {
    return productRepository.findAll();
  }

}
