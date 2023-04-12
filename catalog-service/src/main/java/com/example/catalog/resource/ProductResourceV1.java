package com.example.catalog.resource;

import com.example.catalog.application.command.product.CreateCategoryCommand;
import com.example.catalog.application.command.product.CreateProductCommand;
import com.example.catalog.application.query.product.GetProductListQuery;
import com.example.catalog.application.query.product.GetProductListQuery.ProductFilter;
import com.example.catalog.application.vm.ProductVm;
import com.example.catalog.domain.category.Category;
import com.example.catalog.domain.product.Product;
import com.example.catalog.infrastructure.repository.jpa.JpaProductRepository;
import com.example.common.command.CommandBus;
import com.example.common.query.QueryBus;
import com.example.common.vm.ListQueryResult;
import com.example.common.vm.query.SearchRequest;
import com.example.common.vm.query.SearchSpecification;
import javax.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
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
    private final JpaProductRepository jpaProductRepository;

    @PostMapping("/categories")
    public ResponseEntity<String> createCategory(@RequestBody CreateCategoryCommand command) {
        return ResponseEntity.ok(this.commandBus.execute(command));
    }

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody CreateProductCommand command) {
        return ResponseEntity.ok(this.commandBus.execute(command));
    }

    @PostMapping("/products/list")
    public ResponseEntity<ListQueryResult<ProductVm>> productList(ProductFilter filter, Pageable pageable) {
        GetProductListQuery query = GetProductListQuery.of(filter, pageable);

        return ResponseEntity.ok(this.queryBus.execute(query));
    }

    @PostMapping("/list")
    public Object list(@RequestBody SearchRequest query) {
        SearchSpecification<Product> specification = new SearchSpecification<>(query);
        Pageable pageable = SearchSpecification.getPageable(query.getPage(), query.getSize());

        return this.jpaProductRepository.findAll(specification, pageable);
    }



//    @GetMapping("/{code}")
//    public ResponseEntity<ProductVm> getOne(@PathVariable(value = "code") String code) {
//        GetProductQuery getProductQuery = GetProductQuery.of(code);
//
//        return ResponseEntity.ok(this.queryBus.execute(getProductQuery));
//    }

}
