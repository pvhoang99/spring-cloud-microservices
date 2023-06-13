package com.example.catalog.application.query.product;

import com.example.catalog.application.query.product.GetProductListQuery.ProductFilter;
import com.example.catalog.application.vm.ProductVm;
import com.example.common.query.Query;
import com.example.common.vm.ListQuery;
import com.example.common.vm.ListQueryResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class GetProductListQuery extends ListQuery<ProductFilter> implements Query<ListQueryResult<ProductVm>> {

    public GetProductListQuery(ProductFilter filter, Pageable pageable) {
        super(filter, pageable);
    }

    public static GetProductListQuery of(ProductFilter filter, Pageable pageable) {
        return new GetProductListQuery(filter, pageable);
    }

    @Getter
    @Setter
    public static class ProductFilter {
        public String code;

    }

}
