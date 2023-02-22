package com.example.catalog.domain.product;


import com.example.catalog.application.query.product.GetProductListQuery;
import com.example.catalog.application.query.product.GetProductListQuery.ProductFilter;
import com.example.catalog.application.vm.ProductVm;
import com.example.common.vm.ListQueryResult;

public interface ProductRepository {

    void save(Product product);

    ListQueryResult<ProductFilter, ProductVm> getList(GetProductListQuery query);

}
