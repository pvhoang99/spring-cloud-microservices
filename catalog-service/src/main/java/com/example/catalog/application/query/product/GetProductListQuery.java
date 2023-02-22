package com.example.catalog.application.query.product;

import com.example.catalog.application.query.product.GetProductListQuery.ProductFilter;
import com.example.catalog.application.vm.ProductVm;
import com.example.common.query.Query;
import com.example.common.vm.ListQuery;
import com.example.common.vm.ListQueryResult;
import lombok.Getter;

@Getter
public class GetProductListQuery extends ListQuery<ProductFilter> implements Query<ListQueryResult<ProductFilter, ProductVm>> {

    public static class ProductFilter {

    }

}
