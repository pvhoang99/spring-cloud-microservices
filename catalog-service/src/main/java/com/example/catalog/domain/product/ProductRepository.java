package com.example.catalog.domain.product;

import com.example.catalog.application.query.product.GetProductListQuery;
import com.example.catalog.application.vm.ProductVm;
import com.example.common.vm.ListQueryResult;
import java.util.Set;

public interface ProductRepository {

  void save(Product product);

  Set<ProductVm> getByIds(Set<Long> ids);

  ListQueryResult<ProductVm> getList(GetProductListQuery query);

}
