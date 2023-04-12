package com.example.catalog.infrastructure.repository.mysql;

import com.example.catalog.application.query.product.GetProductListQuery;
import com.example.catalog.application.vm.ProductVm;
import com.example.catalog.domain.product.Product;
import com.example.catalog.domain.product.ProductRepository;
import com.example.catalog.infrastructure.repository.jpa.JpaProductRepository;
import com.example.common.vm.ListQueryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MysqlProductRepository implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public void save(Product product) {
        this.jpaProductRepository.save(product);
    }

    @Override
    public ListQueryResult<ProductVm> getList(GetProductListQuery query) {
        Page<ProductVm> data = this.jpaProductRepository.search(query.getFilters(), query.getPager());

        return ListQueryResult.of(data, query.getPager());
    }

}
