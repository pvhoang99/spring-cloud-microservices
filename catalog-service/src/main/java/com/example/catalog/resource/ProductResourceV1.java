package com.example.catalog.resource;

import com.example.catalog.application.command.product.CreateProductCommand;
import com.example.catalog.application.query.product.GetProductListQuery;
import com.example.catalog.application.query.product.GetProductListQuery.ProductFilter;
import com.example.catalog.application.query.product.GetProductsByIdsQuery;
import com.example.catalog.application.vm.ProductVm;
import com.example.common.command.CommandBus;
import com.example.common.query.QueryBus;
import com.example.common.vm.CommandResult;
import com.example.common.vm.ListQueryResult;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ProductResourceV1 {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    @PostMapping("/products")
    public ResponseEntity<CommandResult<Long>> createProduct(@RequestBody CreateProductCommand command) {
        return ResponseEntity.ok(this.commandBus.execute(command));
    }

    @PostMapping("/products/list")
    public ResponseEntity<ListQueryResult<ProductVm>> productList(ProductFilter filter, Pageable pageable) {
        GetProductListQuery query = GetProductListQuery.of(filter, pageable);

        return ResponseEntity.ok(this.queryBus.execute(query));
    }

    @GetMapping("/products/get-by-ids")
    public ResponseEntity<Set<ProductVm>> getByIds(@RequestBody GetProductsByIdsQuery query) {
        return ResponseEntity.ok(this.queryBus.execute(query));
    }
}
