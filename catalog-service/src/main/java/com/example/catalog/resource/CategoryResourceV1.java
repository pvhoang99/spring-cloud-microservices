package com.example.catalog.resource;

import com.example.catalog.application.command.category.CreateCategoryCommand;
import com.example.catalog.application.query.category.SearchCategoryQuery;
import com.example.common.command.CommandBus;
import com.example.common.query.QueryBus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
public class CategoryResourceV1 {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateCategoryCommand command) {
        return ResponseEntity.ok(this.commandBus.execute(command));
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchCategoryQuery query) {

        return ResponseEntity.ok().build();
    }
}
