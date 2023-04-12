package com.example.inventory.resource;

import com.example.common.command.CommandBus;
import com.example.common.query.QueryBus;
import com.example.inventory.application.query.product.GetProductQuery;
import com.example.inventory.application.vm.ProductVm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

//    @PostMapping
//    public ResponseEntity<String> create(@RequestBody CreateProductCommand command) {
//        return ResponseEntity.ok(this.commandBus.execute(command));
//    }

    @GetMapping("/{code}")
    public ResponseEntity<ProductVm> getOne(@PathVariable(value = "code") String code) {
        GetProductQuery getProductQuery = GetProductQuery.of(code);

        return ResponseEntity.ok(this.queryBus.execute(getProductQuery));
    }

}
