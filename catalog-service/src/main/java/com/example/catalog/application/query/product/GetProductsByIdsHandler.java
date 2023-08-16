package com.example.catalog.application.query.product;

import com.example.catalog.application.vm.ProductVm;
import com.example.catalog.domain.product.ProductRepository;
import com.example.common.query.QueryHandler;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetProductsByIdsHandler implements QueryHandler<GetProductsByIdsQuery, Set<ProductVm>> {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Set<ProductVm> handle(GetProductsByIdsQuery query) {
        return this.productRepository.getByIds(query.getIds());
    }

}
