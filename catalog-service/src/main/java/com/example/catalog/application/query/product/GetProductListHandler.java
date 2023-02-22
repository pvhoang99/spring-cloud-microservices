package com.example.catalog.application.query.product;

import com.example.catalog.application.query.product.GetProductListQuery.ProductFilter;
import com.example.catalog.application.vm.ProductVm;
import com.example.catalog.domain.product.ProductRepository;
import com.example.common.query.QueryHandler;
import com.example.common.vm.ListQueryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetProductListHandler implements
    QueryHandler<GetProductListQuery, ListQueryResult<ProductFilter, ProductVm>> {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public ListQueryResult<ProductFilter, ProductVm> handle(GetProductListQuery query) {

        return null;
    }

}
