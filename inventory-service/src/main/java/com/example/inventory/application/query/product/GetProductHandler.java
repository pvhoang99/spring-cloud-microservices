package com.example.inventory.application.query.product;

import com.example.common.query.QueryHandler;
import com.example.inventory.application.repository.ReadOnlyMysqlProductRepository;
import com.example.inventory.application.vm.ProductVm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetProductHandler implements QueryHandler<GetProductQuery, ProductVm> {

    private final ReadOnlyMysqlProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public ProductVm handle(GetProductQuery query) {
        return this.productRepository.find(query.getCode());
    }

}
